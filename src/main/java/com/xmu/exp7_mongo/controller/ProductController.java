package com.xmu.exp7_mongo.controller;

import com.xmu.exp7_mongo.bo.Product;
import com.xmu.exp7_mongo.controller.dto.ProductDto;
import com.xmu.exp7_mongo.model.ReturnObject;
import com.xmu.exp7_mongo.service.Impl.ProductServiceImpl;
import com.xmu.exp7_mongo.util.CloneFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("")
    public ReturnObject getProductsByName(@RequestParam String name) {
        ReturnObject retObj = null;
        List<Product> productList = productService.getProductsByNameAndRelatedData(name);
        List<ProductDto> data = productList.stream().map(o-> CloneFactory.copy(new ProductDto(),o)).collect(Collectors.toList());
        retObj = new ReturnObject(data);
        return  retObj;
    }
}
