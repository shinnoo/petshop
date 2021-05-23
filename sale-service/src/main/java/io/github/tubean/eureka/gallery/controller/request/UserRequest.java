package io.github.tubean.eureka.gallery.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class UserRequest {
    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @Accessors(chain = true)
    public static class Create{
        @NotNull
        private String name;

        @NotNull
        private String email;

        @NotNull
        private String local;

        @NotNull
        private String phone;

    }
}
