package com.xmu.exp7_mongo.controller;

import com.xmu.exp7_mongo.controller.dto.OrderDto;
import com.xmu.exp7_mongo.controller.dto.OrderItemDto;
import com.xmu.exp7_mongo.controller.vo.OrderVo;
import com.xmu.exp7_mongo.dao.bo.Order;
import com.xmu.exp7_mongo.dao.bo.OrderItem;
import com.xmu.exp7_mongo.model.ReturnNo;
import com.xmu.exp7_mongo.model.ReturnObject;
import com.xmu.exp7_mongo.model.dto.UserDto;
import com.xmu.exp7_mongo.service.OrderService;
import com.xmu.exp7_mongo.util.CloneFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController /*Restful的Controller对象*/
@RequestMapping(produces = "application/json;charset=UTF-8")
public class CustomerController {

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private OrderService orderService;

    @Autowired
    public CustomerController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ReturnObject createOrder(@RequestBody @Validated OrderVo orderVo) {
        Order order = CloneFactory.copy(new Order(), orderVo);
        UserDto user = UserDto.builder().id(1L).name("test").build();
        List<OrderItem> items = orderVo.getItems().stream().map(item -> CloneFactory.copy(new OrderItem(), item)).collect(Collectors.toList());
        order.setOrderItems(items);
        Order newOrder = this.orderService.createOrder(order, user);
        return new ReturnObject(ReturnNo.CREATED);
    }

    @GetMapping("/orders/{id}")
    public ReturnObject getOrders(@PathVariable String id){
        Order order = this.orderService.findOrder(id);
        logger.debug("getOrders: order = {}",order);
        List<OrderItemDto> items  = order.getOrderItems().stream().map(o -> CloneFactory.copy(new OrderItemDto(), o)).collect(Collectors.toList());
        OrderDto dto = CloneFactory.copy(new OrderDto(), order);
        logger.debug("getOrders: items = {}",items);
        dto.setOrderItems(items);
        return new ReturnObject(dto);
    }

}

