package io.github.tubean.eureka.gallery.service.impl;

import io.github.tubean.eureka.gallery.common.error.BadRequestException;
import io.github.tubean.eureka.gallery.common.error.NotFoundException;
import io.github.tubean.eureka.gallery.controller.request.OrderProductRequest;
import io.github.tubean.eureka.gallery.controller.request.OrdersRequest;
import io.github.tubean.eureka.gallery.dto.OrderProductDto;
import io.github.tubean.eureka.gallery.dto.OrdersDto;
import io.github.tubean.eureka.gallery.dto.ProductDto;
import io.github.tubean.eureka.gallery.dto.mapper.CommonMapper;
import io.github.tubean.eureka.gallery.model.OrderProduct;
import io.github.tubean.eureka.gallery.model.Orders;
import io.github.tubean.eureka.gallery.model.Product;
import io.github.tubean.eureka.gallery.repository.OrderProductRepository;
import io.github.tubean.eureka.gallery.repository.OrdersRepository;
import io.github.tubean.eureka.gallery.repository.ProductRepository;
import io.github.tubean.eureka.gallery.repository.UserRepository;
import io.github.tubean.eureka.gallery.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderProductRepository orderProductRepository;
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
        return ordersDto.setOrderProductDtos(orderProductDtos);
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
