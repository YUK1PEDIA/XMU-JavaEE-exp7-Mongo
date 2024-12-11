//School of Informatics Xiamen University, GPL-3.0 license

package com.xmu.exp7_mongo.service;

import com.xmu.exp7_mongo.dao.OrderDao;
import com.xmu.exp7_mongo.dao.bo.Order;
import com.xmu.exp7_mongo.dao.bo.OrderItem;
import com.xmu.exp7_mongo.model.dto.UserDto;
import com.xmu.exp7_mongo.util.SnowFlakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderService {

    private OrderDao orderDao;

    private SnowFlakeIdWorker snowFlakeIdWorker;

    @Autowired
    public OrderService(OrderDao orderDao, SnowFlakeIdWorker snowFlakeIdWorker) {
        this.orderDao = orderDao;
        this.snowFlakeIdWorker = snowFlakeIdWorker;
    }

    public Order createOrder(Order order, UserDto customer) {
        order.setOrderSn(String.format("O%d", this.snowFlakeIdWorker.nextId()));
        for (OrderItem item : order.getOrderItems()){
            item.setId(this.snowFlakeIdWorker.nextId());
        }
        return this.orderDao.insert(order);
    }

    public Order findOrder(String id){
        return this.orderDao.findById(id);
    }
}
