package io.github.tubean.eureka.gallery.repository;

import io.github.tubean.eureka.gallery.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct,String> {
}
