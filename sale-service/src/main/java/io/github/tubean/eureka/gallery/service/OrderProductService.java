package io.github.tubean.eureka.gallery.service;

import io.github.tubean.eureka.gallery.common.constant.ResultPage;
import io.github.tubean.eureka.gallery.controller.request.OrdersRequest;
import io.github.tubean.eureka.gallery.dto.OrderProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

public interface OrderProductService {
    OrderProductDto create(OrdersRequest.Create request);

    Page<OrderProductDto> getAllByOrderId(String orderId, Pageable pageable, MultiValueMap<String, String> where);

}
