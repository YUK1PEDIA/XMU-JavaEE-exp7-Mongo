package com.xmu.exp7_mongo.util;

import com.xmu.exp7_mongo.bo.OnSale;
import com.xmu.exp7_mongo.bo.Product;
import com.xmu.exp7_mongo.controller.dto.ProductDto;
import com.xmu.exp7_mongo.entity.OnSalePo;
import com.xmu.exp7_mongo.entity.ProductPo;

import java.util.stream.Collectors;

public class CloneFactory {

    /**
     * Product 转成 ProductDto
     * @param target
     * @param source
     * @return
     */
    public static ProductDto copy(ProductDto target, Product source){
        target = ProductDto.builder().id(source.getId()).skuSn(source.getSkuSn()).name(source.getName()).unit(source.getUnit()).originalPrice(source.getOriginalPrice()).barcode(source.getBarcode())
                .weight(source.getWeight()).originPlace(source.getOriginPlace()).build();
        if (source.getOnSaleList().size() == 1){
            OnSale onSale = source.getOnSaleList().get(0);
            target.setPrice(onSale.getPrice());
            target.setQuantity(onSale.getQuantity());
            target.setMaxQuantity(onSale.getMaxQuantity());
        }

        target.setOtherProduct(source.getOtherProduct().stream().map(o->CloneFactory.copy(new ProductDto(), o)).collect(Collectors.toList()));
        return target;
    }

    /**
     * ProductPo 转成 Product
     * @param target
     * @param source
     * @return
     */
    public static Product copy(Product target, ProductPo source) {
        target = Product.builder()
                .id(source.getId())
                .skuSn(source.getSkuSn())
                .name(source.getName())
                .originalPrice(source.getOriginalPrice())
                .weight(source.getWeight())
                .barcode(source.getBarcode())
                .unit(source.getUnit())
                .originPlace(source.getOriginPlace())
                .commissionRatio(source.getCommissionRatio())
                .freeThreshold(source.getFreeThreshold())
                .status(source.getStatus() != null ? source.getStatus() : 0)
                .gmtCreate(source.getGmtCreate())
                .gmtModified(source.getGmtModified())
                .build();
        return target;
    }

    /**
     * 将 OnsalePo 转成 OnSale
     * @param target
     * @param source
     * @return
     */
    public static OnSale copy(OnSale target, OnSalePo source) {
        target = OnSale.builder()
                .id(source.getId())
                .price(source.getPrice())
                .beginTime(source.getBeginTime())
                .endTime(source.getEndTime())
                .quantity(source.getQuantity())
                .maxQuantity(source.getMaxQuantity())
                .gmtCreate(source.getGmtCreate())
                .gmtModified(source.getGmtModified())
                .build();
        return target;
    }


}
