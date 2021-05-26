package com.ptit.sale.service;

import com.ptit.sale.controller.request.OrdersRequest;
import com.ptit.sale.dto.OrderProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface OrderProductService {
    OrderProductDto create(OrdersRequest.Create request);

    List<OrderProductDto> getAllByOrderId(String orderId, Pageable pageable, MultiValueMap<String, String> where);

}
