package com.ptit.sale.service;

import com.ptit.sale.controller.request.ProductRequest;
import com.ptit.sale.dto.ProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductRequest.Create request);

    ProductDto update(String id,ProductRequest.Update request);

    List<ProductDto> getAll(Pageable pageable, MultiValueMap<String,String> where);

    void delete(String productId);


}
