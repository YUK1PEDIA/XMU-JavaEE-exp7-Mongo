package com.xmu.exp7_mongo.bo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable; // 导入 Serializable 接口
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OnSale implements Serializable {

    private Long id;

    private Long price;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private Integer quantity;

    private Integer maxQuantity;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    @Builder
    public OnSale(Long id, Long price, LocalDateTime beginTime, LocalDateTime endTime, Integer quantity, Integer maxQuantity, LocalDateTime gmtCreate, LocalDateTime gmtModified) {
        this.id = id;
        this.price = price;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
}
