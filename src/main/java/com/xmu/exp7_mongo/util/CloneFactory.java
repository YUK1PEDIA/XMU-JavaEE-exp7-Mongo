package com.xmu.exp7_mongo.util;

import com.xmu.exp7_mongo.bo.OnSale;
import com.xmu.exp7_mongo.bo.Product;
import com.xmu.exp7_mongo.controller.dto.OrderDto;
import com.xmu.exp7_mongo.controller.dto.OrderItemDto;
import com.xmu.exp7_mongo.controller.dto.ProductDto;
import com.xmu.exp7_mongo.controller.vo.OrderItemVo;
import com.xmu.exp7_mongo.controller.vo.OrderVo;
import com.xmu.exp7_mongo.dao.bo.Order;
import com.xmu.exp7_mongo.dao.bo.OrderItem;
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

    /**
     * 将 OrderVo 转成 Order
     * @param target
     * @param source
     * @return
     */
    public static  Order copy(Order target, OrderVo source){
        return Order.builder()
                .consignee(source.getConsignee())
                .address(source.getAddress())
                .message(source.getMessage())
                .regionId(source.getRegionId())
                .mobile(source.getMobile())
                .build();
    }

    /**
     * 将 OrderItemVo 转成 OrderItem
     * @param target
     * @param source
     * @return
     */
    public static OrderItem copy(OrderItem target, OrderItemVo source){
        return OrderItem.builder()
                .actId(source.getActId())
                .couponId(source.getCouponId())
                .onsaleId(source.getOnsaleId())
                .quantity(source.getQuantity())
                .build();
    }

    /**
     * 将 Order 转成 OrderDto
     * @param target
     * @param source
     * @return
     */
    public static OrderDto copy(OrderDto target, Order source){
        return OrderDto.builder()
                .id(source.getId())
                .address(source.getAddress())
                .mobile(source.getMobile())
                .orderSn(source.getOrderSn())
                .consignee(source.getConsignee())
                .build();
    }

    /**
     * 将 OrderItem 转成 OrderItemDto
     * @param target
     * @param source
     * @return
     */
    public static OrderItemDto copy(OrderItemDto target, OrderItem source){
        return OrderItemDto.builder()
                .id(source.getId())
                .onsaleId(source.getOnsaleId())
                .price(source.getPrice())
                .quantity(source.getQuantity())
                .name(source.getName())
                .point(source.getPoint())
                .discountPrice(source.getDiscountPrice())
                .build();
    }
}
