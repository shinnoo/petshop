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
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class OrdersDto {
    private String id;

    private String status;

    private BigDecimal totalPrice;

    private String userId;

    private Instant createAt;

    @JsonGetter("create_at")
    public Object getCreateAt(){
        try {
            return createAt.getEpochSecond();
        }catch (Exception e){
            return  null;
        }
    }

    private List<OrderProductDto> orderProductDtos;
}
