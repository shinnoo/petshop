package io.github.tubean.eureka.gallery.service.impl;

import io.github.tubean.eureka.gallery.common.constant.ResultPage;
import io.github.tubean.eureka.gallery.common.error.BadRequestException;
import io.github.tubean.eureka.gallery.common.error.NotFoundException;
import io.github.tubean.eureka.gallery.controller.request.ProductRequest;
import io.github.tubean.eureka.gallery.dto.ProductDto;
import io.github.tubean.eureka.gallery.dto.mapper.CommonMapper;
import io.github.tubean.eureka.gallery.model.Product;
import io.github.tubean.eureka.gallery.repository.ProductDetailRepository;
import io.github.tubean.eureka.gallery.repository.ProductRepository;
import io.github.tubean.eureka.gallery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Override
    public ProductDto create(ProductRequest.Create request) {
        if(productRepository.existsByCode(request.getCode())) {
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
    public Page<ProductDto> getAll(Pageable pageable, MultiValueMap<String, String> where) {
        ResultPage<Product> page = productDetailRepository.getAllWithFilter(pageable, where);
        Long total = page.getTotalItems();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : page.getPageList()) {
            productDtos
                    .add(CommonMapper.map(product, ProductDto.class));
        }
        return new PageImpl<ProductDto>(productDtos,pageable,total);
    }

    @Override
    public void delete(String productId) {

    }
}
