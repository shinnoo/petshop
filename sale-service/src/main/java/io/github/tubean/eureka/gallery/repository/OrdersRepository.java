package io.github.tubean.eureka.gallery.repository;

import io.github.tubean.eureka.gallery.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,String> {
	List<Orders> findAllByUserId(String userId);
}
