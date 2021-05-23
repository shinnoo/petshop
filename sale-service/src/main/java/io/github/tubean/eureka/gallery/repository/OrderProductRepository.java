package io.github.tubean.eureka.gallery.repository;

import io.github.tubean.eureka.gallery.model.OrderProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct,String> {
	List<OrderProduct> findAllByOrderId(String orderId, Pageable pageable);
}
