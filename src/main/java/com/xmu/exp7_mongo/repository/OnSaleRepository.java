package com.xmu.exp7_mongo.repository;

import com.xmu.exp7_mongo.entity.OnSalePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnSaleRepository extends JpaRepository<OnSalePo, Long> {

    // 根据productId查询OnSale
    List<OnSalePo> findByProductId(Long productId);

}
