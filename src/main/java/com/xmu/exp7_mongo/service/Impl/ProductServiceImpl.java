package com.xmu.exp7_mongo.service.Impl;

import com.xmu.exp7_mongo.bo.OnSale;
import com.xmu.exp7_mongo.bo.Product;
import com.xmu.exp7_mongo.entity.OnSalePo;
import com.xmu.exp7_mongo.entity.ProductPo;
import com.xmu.exp7_mongo.repository.OnSaleRepository;
import com.xmu.exp7_mongo.repository.ProductRepository;
import com.xmu.exp7_mongo.service.ProductService;
import com.xmu.exp7_mongo.util.CloneFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OnSaleRepository onSaleRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Product> getProductsByNameAndRelatedData(String name) {
        String productCacheKey = "product:" + name;
        String onSalePoCacheKey = "onSalePo:" + name;
        String allProductsKey = "allProducts:" + name;

        // 从缓存中获取数据
        List<Product> cachedProduct = (List<Product>) redisTemplate.opsForValue().get(productCacheKey);
        if (cachedProduct != null) {
            log.info("Cache hit for product: " + productCacheKey);
            return cachedProduct;
        }

        // 如果缓存中没有数据，查询数据库
        ProductPo productPo = productRepository.findByName(name);
        if (productPo == null) {
            throw new RuntimeException("Product not found with name: " + name);
        }

        // 先查 redis
        List<OnSalePo> cachedOnSalePos = (List<OnSalePo>) redisTemplate.opsForValue().get(onSalePoCacheKey);
        if (cachedOnSalePos != null) {
            log.info("Cache hit for onsale: " + onSalePoCacheKey);
        } else {
            // redis 不存在，再查数据库
            cachedOnSalePos = onSaleRepository.findByProductId(productPo.getId());

            // 添加到 redis 中
            redisTemplate.opsForValue().set(onSalePoCacheKey, cachedOnSalePos, 1, TimeUnit.HOURS);
        }

        List<OnSale> onSales = new ArrayList<>();
        for (OnSalePo cachedOnSalePo : cachedOnSalePos) {
            onSales.add(CloneFactory.copy(new OnSale(), cachedOnSalePo));
        }

        // 查询相关的其他产品
        List<ProductPo> allProducts = (List<ProductPo>) redisTemplate.opsForValue().get(allProductsKey);
        if (allProducts != null) {
            log.info("Cache hit for allProducts：" + allProductsKey);
        } else {
            // redis 中不存在，查数据库
            allProducts = productRepository.findByGoodsId(productPo.getGoodsId());
            // 存到 redis 中
            redisTemplate.opsForValue().set(allProductsKey, allProducts, 1, TimeUnit.HOURS);
        }

        List<Product> otherProduct = new ArrayList<>();

        // 转换 ProductPo 到 Product
        Product product = CloneFactory.copy(new Product(), productPo);
        // 添加其他 product
        for (ProductPo allProduct : allProducts) {
            if (!name.equals(allProduct.getName())) {
                otherProduct.add(CloneFactory.copy(new Product(), allProduct));
            }
        }

        product.setOnSaleList(onSales);
        product.setOtherProduct(otherProduct);

        // 最终数据存到 redis
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        redisTemplate.opsForValue().set(productCacheKey, productList, 1, TimeUnit.HOURS);

        // 返回最终的数据
        return productList;
    }

}
