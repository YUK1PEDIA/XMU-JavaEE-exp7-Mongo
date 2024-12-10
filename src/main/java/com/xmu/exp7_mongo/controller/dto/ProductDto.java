package com.xmu.exp7_mongo.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDto {

    private Long id;

    private String skuSn;

    private String name;

    private Long originalPrice;

    private Long weight;

    private Long price;

    private String barcode;

    private String unit;

    private String originPlace;

    private Integer quantity;

    private Integer maxQuantity;

    private List<ProductDto> otherProduct;

}
