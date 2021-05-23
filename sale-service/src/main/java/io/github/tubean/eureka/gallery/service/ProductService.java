package io.github.tubean.eureka.gallery.service;

import io.github.tubean.eureka.gallery.common.constant.ResultPage;
import io.github.tubean.eureka.gallery.controller.request.OrderProductRequest;
import io.github.tubean.eureka.gallery.controller.request.ProductRequest;
import io.github.tubean.eureka.gallery.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductRequest.Create request);

    ProductDto update(String id,ProductRequest.Update request);

    List<ProductDto> getAll(Pageable pageable, MultiValueMap<String,String> where);

    void delete(String productId);


}
