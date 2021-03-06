package com.ptit.sale.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrdersRequest {
    @Data
    @Accessors(chain = true)
    public static class Create{
        @NotNull(message = "user_id can not be null")
        private String userId;

        @NotNull(message = "total_price can not be null")
        private Long totalPrice;

        @Valid
        private List<OrderProductRequest.Create> orderProducts;

    }
    //
    @Data
    @Accessors(chain = true)
    public static class Update{
        private String userId;

        private String status;

        private Long totalPrice;
    }
}
