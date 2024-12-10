//School of Informatics Xiamen University, GPL-3.0 license

package com.xmu.exp7_mongo.dao;

import com.xmu.exp7_mongo.dao.bo.Order;
import com.xmu.exp7_mongo.exception.BusinessException;
import com.xmu.exp7_mongo.mapper.OrderMapper;
import com.xmu.exp7_mongo.model.ReturnNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderDao {

    private OrderMapper orderMapper;

    @Autowired
    public OrderDao(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }


    public Order insert(Order order){
        return this.orderMapper.insert(order);
    }

    public Order findById(String id){
        Optional<Order> ret = this.orderMapper.findById(id);
        if (ret.isEmpty()){
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST);
        }else{
            return ret.get();
        }
    }
}
