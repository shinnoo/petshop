package io.github.tubean.eureka.gallery.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDto {
    private String id;

    private String name;

    private String imgUrl;

    private String type;

    private Long price;

    private String description;

    private Integer age;

    private Integer weight;
    private String sex;
    private String color;
}
