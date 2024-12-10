package com.xmu.exp7_mongo.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Data
public class OrderItemDto{

    private Long id;

    private Long onsaleId;

    private Integer quantity;

    private Long price;

    private Long discountPrice;

    private Long point;

    private String name;
}
