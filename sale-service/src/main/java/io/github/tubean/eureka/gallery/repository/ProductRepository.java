package io.github.tubean.eureka.gallery.repository;

import io.github.tubean.eureka.gallery.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findAllByType(String type, Pageable pageable);
}
