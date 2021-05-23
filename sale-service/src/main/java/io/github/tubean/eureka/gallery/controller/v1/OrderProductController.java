package io.github.tubean.eureka.gallery.controller.v1;

import io.github.tubean.eureka.gallery.common.constant.ResultPage;
import io.github.tubean.eureka.gallery.common.enums.ResponseCodeEnum;
import io.github.tubean.eureka.gallery.controller.response.ResponseBodyDto;
import io.github.tubean.eureka.gallery.dto.OrderProductDto;
import io.github.tubean.eureka.gallery.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class OrderProductController {
    @Autowired
    private OrderProductService orderProductService;

    @GetMapping(value = "/orders/{order-id}/order-products", produces = "application/json")
    public ResponseEntity<ResponseBodyDto<OrderProductDto>> getAll(Pageable pageable,
                                                                   @RequestParam MultiValueMap<String, String> where, @PathVariable("order-id") String orderId) {
        Page<OrderProductDto> page = orderProductService.getAllByOrderId(orderId, pageable, where);
        ResponseBodyDto<OrderProductDto> response = new ResponseBodyDto<OrderProductDto>(pageable, page,
                ResponseCodeEnum.R_200, "OK");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
