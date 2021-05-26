package com.ptit.sale.repository;

import com.ptit.sale.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,String> {
	List<Orders> findAllByUserId(String userId);
}
