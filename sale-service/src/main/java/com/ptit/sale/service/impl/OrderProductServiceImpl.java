package com.ptit.sale.service.impl;

import com.ptit.sale.common.error.NotFoundException;
import com.ptit.sale.controller.request.OrdersRequest;
import com.ptit.sale.dto.OrderProductDto;
import com.ptit.sale.dto.mapper.CommonMapper;
import com.ptit.sale.model.OrderProduct;
import com.ptit.sale.model.Orders;
import com.ptit.sale.repository.OrderProductRepository;
import com.ptit.sale.repository.OrdersRepository;
import com.ptit.sale.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class OrderProductServiceImpl implements OrderProductService
{
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderProductRepository orderProductRepository;

    @Override
    public OrderProductDto create(OrdersRequest.Create request) {
        return null;
    }

    @Override
    public List<OrderProductDto> getAllByOrderId(String orderId, Pageable pageable,
                                                 MultiValueMap<String, String> where) {
        Orders order = ordersRepository.findById(orderId).orElse(null);
        if (null == order) {
            throw new NotFoundException("Không tìm thấy đơn hàng");
        }
        List<OrderProduct> orderProducts = orderProductRepository.findAllByOrderId(orderId, pageable);
        List<OrderProductDto> orderProductDtos = new ArrayList<>();
        for (OrderProduct orderProduct : orderProducts){
            orderProductDtos.add(CommonMapper.map(orderProduct,OrderProductDto.class));
        }
        return orderProductDtos;
    }

}
