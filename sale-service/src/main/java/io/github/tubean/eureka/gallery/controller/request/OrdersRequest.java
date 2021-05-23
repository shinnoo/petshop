package io.github.tubean.eureka.gallery.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.tubean.eureka.gallery.model.OrderProduct;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class OrdersRequest {
    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @Accessors(chain = true)
    public static class Create{
        @NotNull(message = "user_id can not be null")
        private String userId;

        @NotNull(message = "status can not be null")
        private String status;

        @NotNull(message = "total_price can not be null")
        private BigDecimal totalPrice;

        @Valid
        private List<OrderProductRequest.Create> orderProducts;

    }
    //
    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @Accessors(chain = true)
    public static class Update{
        private String userId;

        private String status;

        private BigDecimal totalPrice;
    }
}