package io.github.tubean.eureka.gallery.service.impl;

import io.github.tubean.eureka.gallery.common.constant.ResultPage;
import io.github.tubean.eureka.gallery.common.error.NotFoundException;
import io.github.tubean.eureka.gallery.controller.request.OrdersRequest;
import io.github.tubean.eureka.gallery.dto.OrderProductDto;
import io.github.tubean.eureka.gallery.dto.mapper.CommonMapper;
import io.github.tubean.eureka.gallery.model.OrderProduct;
import io.github.tubean.eureka.gallery.model.Orders;
import io.github.tubean.eureka.gallery.repository.OrderProductRepository;
import io.github.tubean.eureka.gallery.repository.OrdersRepository;
import io.github.tubean.eureka.gallery.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
