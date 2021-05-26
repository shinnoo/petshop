package com.ptit.sale.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OrderProductRequest {
    @Data
    @Accessors(chain = true)
    public static class Create{
        @NotNull(message = "product_id can not be null")
        private String productId;

        @NotNull(message = "quantity can not be null")
        private Integer quantity;

        @NotNull(message = "unit_price can not be null")
        private Long unitPrice;

        private String productName;

    }
    //
    @Data
    @Accessors(chain = true)
    public static class Update{
        private String orderId;

        private String productId;

        private Integer quantity;
    }
}
