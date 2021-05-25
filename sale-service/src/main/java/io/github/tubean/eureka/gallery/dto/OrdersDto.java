package io.github.tubean.eureka.gallery.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
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
