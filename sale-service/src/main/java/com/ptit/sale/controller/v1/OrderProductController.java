package com.ptit.sale.controller.v1;

import com.ptit.sale.dto.OrderProductDto;
import com.ptit.sale.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class OrderProductController {
    @Autowired
    private OrderProductService orderProductService;

    @GetMapping(value = "/orders/{order-id}/order-products")
    public ResponseEntity<List<OrderProductDto>> getAll(Pageable pageable,
                                                                   @RequestParam MultiValueMap<String, String> where, @PathVariable("order-id") String orderId) {
        List<OrderProductDto> page = orderProductService.getAllByOrderId(orderId, pageable, where);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}
