package com.ptit.sale.service.impl;

import com.ptit.sale.common.error.BadRequestException;
import com.ptit.sale.common.error.NotFoundException;
import com.ptit.sale.controller.request.ProductRequest;
import com.ptit.sale.dto.ProductDto;
import com.ptit.sale.dto.mapper.CommonMapper;
import com.ptit.sale.model.Product;
import com.ptit.sale.repository.ProductRepository;
import com.ptit.sale.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
	ProductRepository productRepository;
    @Override
    public ProductDto create(ProductRequest.Create request) {
        if(productRepository.existsById(request.getCode())) {
            throw new BadRequestException("Mã sản phẩm đã tồn tại");
        }
        Product product = new Product();
        CommonMapper.copyPropertiesIgnoreNull(request,product);
        product= productRepository.save(product);
        return CommonMapper.map(product,ProductDto.class);
    }

    @Override
    public ProductDto update(String id, ProductRequest.Update request) {
        Optional<Product> optional = productRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundException("Mã khách hàng không tồn tại");
        }
        Product product = optional.get();
        CommonMapper.copyPropertiesIgnoreNull(request,product);
        product = productRepository.save(product);
        return CommonMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAll(Pageable pageable, MultiValueMap<String, String> where) {
        List<Product> products = new ArrayList<>();
        if (where.containsKey("type")){
            products = productRepository.findAllByType(where.get("type").get(0), pageable);
        } else {
            products = productRepository.findAll(pageable).getContent();
        }
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(CommonMapper.map(product, ProductDto.class));
        }
        return productDtos;
    }

    @Override
    public void delete(String productId) {

    }
}
