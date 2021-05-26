package com.ptit.sale.controller.v1;

import com.ptit.sale.controller.request.ProductRequest;
import com.ptit.sale.dto.ProductDto;
import com.ptit.sale.model.Product;
import com.ptit.sale.repository.ProductRepository;
import com.ptit.sale.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Validated
public class ProductController {
    @Autowired
    ProductService productService;


    @Autowired
    ProductRepository productRepository;
    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDto>> getAll(Pageable pageable,
                                                              @RequestParam MultiValueMap<String, String> queryParams) {
        List<ProductDto> resultPageDto = productService.getAll(pageable, queryParams);
        return new ResponseEntity<>(resultPageDto, HttpStatus.OK);
    }

    //
    @PostMapping(value = "/products",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> create(
            @RequestBody ProductRequest.Create request
    ){
        ProductDto productDto= productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }
    //update cusstomer
    @PatchMapping(value = "/products/{product-id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> update(
            @PathVariable(name = "product-id") String productId,
            @RequestBody ProductRequest.Update request
    ){
        ProductDto productDto= productService.update(productId,request);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getAll(@PathVariable String id) {
        Product product = productRepository.findById(id).get();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
