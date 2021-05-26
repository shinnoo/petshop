package com.ptit.sale.service.impl;

import com.ptit.sale.controller.request.OrdersRequest;
import com.ptit.sale.dto.OrderProductDto;
import com.ptit.sale.dto.OrdersDto;
import com.ptit.sale.dto.mapper.CommonMapper;
import com.ptit.sale.model.OrderProduct;
import com.ptit.sale.model.Orders;
import com.ptit.sale.service.MailService;
import com.ptit.sale.repository.OrderProductRepository;
import com.ptit.sale.repository.OrdersRepository;
import com.ptit.sale.repository.ProductRepository;
import com.ptit.sale.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    MailService mailService;

    @Autowired
    RestTemplate restTemplate;
    @Override
    public OrdersDto create(OrdersRequest.Create request) {
        Orders orders = CommonMapper.createOrderRequestToOrder(request);
        orders.setCreateAt(Instant.now());
        orders.setStatus("Đã đặt đơn");
        OrdersDto ordersDto = CommonMapper.orderToOrderDto(ordersRepository.save(orders));
        List<OrderProduct> orderProducts = CommonMapper.toList(request.getOrderProducts(),OrderProduct.class);
        orderProducts.forEach(i -> i.setOrderId(ordersDto.getId()));
        orderProducts = orderProductRepository.saveAll(orderProducts);
        List<OrderProductDto> orderProductDtos = CommonMapper.toList(orderProducts,OrderProductDto.class);


        // Async task send mail
        mailService.sendMail(orders);

        return ordersDto.setOrderProducts(orderProductDtos);
    }



    @Override
    public OrdersDto update(OrdersRequest.Update request) {
        return null;
    }

    @Override
    public List<Orders> getAllByUserId(String userId) {
        return ordersRepository.findAllByUserId(userId);
    }


}
