package io.github.tubean.eureka.gallery.repository;

import io.github.tubean.eureka.gallery.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
    Product findByCode(String code);
    Boolean existsByCode(String code);
}
