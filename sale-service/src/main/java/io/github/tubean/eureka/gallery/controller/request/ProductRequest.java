package io.github.tubean.eureka.gallery.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductRequest {
    @Data
    @Accessors(chain = true)
    public static class Create{
        @NotNull(message = "name can not be null")
        private String name;

        @NotNull(message = "type can not be null")
        private String type;

        @NotNull(message = "imgUrl can not be null")
        private String imgUrl;

        @NotNull(message = "code can not be null")
        private String code;

        @NotNull(message = "total_quantity can not be null")
        private Float totalQuantity;

        @NotNull(message = "price can not be null")
        private Float price;

    }
    //
    @Data
    @Accessors(chain = true)
    public static class Update{
        private String name;

        private String type;

        private String imgUrl;

        private String code;

        private Float totalQuantity;

        private Float price;
    }

}
