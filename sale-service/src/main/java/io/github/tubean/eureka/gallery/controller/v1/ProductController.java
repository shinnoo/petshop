package io.github.tubean.eureka.gallery.controller.v1;

import io.github.tubean.eureka.gallery.common.enums.ResponseCodeEnum;
import io.github.tubean.eureka.gallery.controller.request.ProductRequest;
import io.github.tubean.eureka.gallery.controller.response.ResponseBodyDto;
import io.github.tubean.eureka.gallery.dto.ProductDto;
import io.github.tubean.eureka.gallery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

@RestController
@RequestMapping("/v1")
@Validated
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<ResponseBodyDto<ProductDto>> getAll(Pageable pageable,
                                                              @RequestParam MultiValueMap<String, String> queryParams) {
        Page<ProductDto> resultPageDto = productService.getAll(pageable, queryParams);
        ResponseBodyDto<ProductDto> response = new ResponseBodyDto<>(pageable, resultPageDto, ResponseCodeEnum.R_200, "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //
    @PostMapping(value = "/products",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<ProductDto>> create(
            @RequestBody ProductRequest.Create request
    ){
        ProductDto productDto= productService.create(request);
        ResponseBodyDto<ProductDto> res= new ResponseBodyDto<ProductDto>(productDto,ResponseCodeEnum.R_201,"CREATED");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    //update cusstomer
    @PatchMapping(value = "/products/{product-id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<ProductDto>> update(
            @PathVariable(name = "product-id") String productId,
            @RequestBody ProductRequest.Update request
    ){
        ProductDto productDto= productService.update(productId,request);
        ResponseBodyDto<ProductDto> res= new ResponseBodyDto<ProductDto>(productDto,ResponseCodeEnum.R_200,"OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
