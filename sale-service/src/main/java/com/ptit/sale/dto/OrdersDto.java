package com.ptit.sale.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class OrdersDto {
    private String id;

    private String status;

    private Long totalPrice;

    private String userId;

    private Instant createAt;

    private List<OrderProductDto> orderProducts;
}
