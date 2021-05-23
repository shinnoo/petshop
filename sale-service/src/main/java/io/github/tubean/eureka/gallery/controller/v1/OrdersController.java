package io.github.tubean.eureka.gallery.controller.v1;

import io.github.tubean.eureka.gallery.common.enums.ResponseCodeEnum;
import io.github.tubean.eureka.gallery.controller.request.OrdersRequest;
import io.github.tubean.eureka.gallery.controller.response.ResponseBodyDto;
import io.github.tubean.eureka.gallery.dto.OrdersDto;
import io.github.tubean.eureka.gallery.model.Orders;
import io.github.tubean.eureka.gallery.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/v1")
@Validated
public class OrdersController
{
    @Autowired
    OrdersService ordersService;
    @PostMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<OrdersDto> createOrder(
            @RequestBody @Valid OrdersRequest.Create createOrderRequest)
            throws IOException, InterruptedException {
        OrdersDto orderDto = ordersService.create(createOrderRequest);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/orders/users/{user-id}", produces = "application/json")
    public ResponseEntity<List<Orders>> getAllByUserId(@PathVariable("user-id") String userId){
        List<Orders> orderDto = ordersService.getAllByUserId(userId);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }
}
