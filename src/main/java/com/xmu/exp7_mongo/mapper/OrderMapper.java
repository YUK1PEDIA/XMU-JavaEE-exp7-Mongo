package com.xmu.exp7_mongo.mapper;

import com.xmu.exp7_mongo.dao.bo.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends MongoRepository<Order, String>{

    Order findFirstByOrderSn(String orderSn);
}
