package io.github.tubean.eureka.gallery.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OrderProductRequest {
    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @Accessors(chain = true)
    public static class Create{
        @NotNull(message = "order_id can not be null")
        private String orderId;

        @NotNull(message = "product_id can not be null")
        private String productId;

        @NotNull(message = "quantity can not be null")
        private Float quantity;

        @NotNull(message = "sum_price can not be null")
        private Float sumPrice;

        private Float discount;

    }
    //
    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @Accessors(chain = true)
    public static class Update{
        private String orderId;

        private String productId;

        private BigDecimal quantity;
    }
}
