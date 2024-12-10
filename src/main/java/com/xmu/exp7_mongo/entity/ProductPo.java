package com.xmu.exp7_mongo.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "goods_product")
public class ProductPo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shopId;
    private Long goodsId;
    private Long categoryId;
    private Long templateId;
    private String skuSn;
    private String name;
    private Long originalPrice;
    private Long weight;
    private String barcode;
    private String unit;
    private String originPlace;
    private Long creatorId;
    private String creatorName;
    private Long modifierId;
    private String modifierName;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Byte status;
    private Integer commissionRatio;
    private Long shopLogisticId;
    private Long freeThreshold;
}

