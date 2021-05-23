package io.github.tubean.eureka.gallery.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;


@Data
@NoArgsConstructor
@Accessors(chain = true)
public class OrderProductDto {
    private String id;
    private String orderId;
    private String productId;
    private Integer quantity;
    private Long unitPrice;
    private String productName;
}
