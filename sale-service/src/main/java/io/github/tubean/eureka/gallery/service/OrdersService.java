package io.github.tubean.eureka.gallery.service;

import io.github.tubean.eureka.gallery.controller.request.OrdersRequest;
import io.github.tubean.eureka.gallery.dto.OrdersDto;

import java.io.IOException;
import java.util.Map;

public interface OrdersService {
    OrdersDto create(OrdersRequest.Create request);

    OrdersDto update(OrdersRequest.Update request);

}
