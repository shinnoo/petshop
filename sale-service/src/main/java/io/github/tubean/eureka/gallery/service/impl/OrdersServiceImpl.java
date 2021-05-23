package io.github.tubean.eureka.gallery.service.impl;

import io.github.tubean.eureka.gallery.common.error.BadRequestException;
import io.github.tubean.eureka.gallery.common.error.NotFoundException;
import io.github.tubean.eureka.gallery.controller.request.OrderProductRequest;
import io.github.tubean.eureka.gallery.controller.request.OrdersRequest;
import io.github.tubean.eureka.gallery.dto.OrderProductDto;
import io.github.tubean.eureka.gallery.dto.OrdersDto;
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
        if (!userRepository.existsById(request.getUserId())){
            throw new BadRequestException("Khách hàng không tồn tại");
        }
        float totalPrice = 0f;
        float totalQuantity = 0f;
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductRequest.Create i : request.getOrderProducts()){
            Product product = productRepository.findById(i.getProductId()).orElse(null);
            if (null == product){
                throw new NotFoundException(
                        "Sản phẩm " + "(" + i.getProductId() + ") không tồn tại");
            }
            if (i.getQuantity() > product.getTotalQuantity()){
                throw new BadRequestException("Xin lỗi số lượng sản phẩm này không đủ đáp ứng đơn hàng");
            }
            OrderProduct orderProduct =CommonMapper.createOrderProductRequestToOrderProduct(i);
            // Tính total price của từng product trong đơn hàng
            float sumPrice = (product.getPrice() - (orderProduct.getDiscount() == null ? 0F : orderProduct.getDiscount()))
                    * orderProduct.getQuantity();
            orderProducts.add(orderProduct.setSumPrice(sumPrice));
            totalPrice += sumPrice;
            product.setTotalQuantity(product.getTotalQuantity() - orderProduct.getQuantity());
            productRepository.save(product);
        }
        Orders orders = CommonMapper.createOrderRequestToOrder(request);
        orders.setStatus("CREATE").setCreateAt(Instant.now());
        OrdersDto ordersDto = CommonMapper.orderToOrderDto(ordersRepository.save(orders));
        orderProducts.forEach(i -> i.setOrderId(ordersDto.getId()));
        orderProducts = orderProductRepository.saveAll(orderProducts);
        List<OrderProductDto> orderProductDtos = CommonMapper.orderProductsToOrderProductDtos(orderProducts);

        return ordersDto.setOrderProductDtos(orderProductDtos);

//        Orders orders = new Orders();
//        CommonMapper.copyPropertiesIgnoreNull(request,Orders.class);
//        orders.setCreateAt(Instant.now());
//        orders.setStatus("Đã đặt đơn");
//        orders = ordersRepository.save(orders);
//        return  CommonMapper.map(orders,OrdersDto.class);
    }

    @Override
    public OrdersDto update(OrdersRequest.Update request) {
        return null;
    }



}
