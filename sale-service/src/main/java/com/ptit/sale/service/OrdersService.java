package com.ptit.sale.service;

import com.ptit.sale.controller.request.OrdersRequest;
import com.ptit.sale.dto.OrdersDto;
import com.ptit.sale.model.Orders;

import java.util.List;

public interface OrdersService {
    OrdersDto create(OrdersRequest.Create request);

    OrdersDto update(OrdersRequest.Update request);

    List<Orders> getAllByUserId(String userId);
}
