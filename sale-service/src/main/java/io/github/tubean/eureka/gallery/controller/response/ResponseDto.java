package io.github.tubean.eureka.gallery.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class ResponseDto<E> {

    private String code;

    private String message;

    private long totalItems;

    private int size;

    private int page;

    /**
     * Reset API: Response data of 1 item
     */
    private E item;

    private List<E> items;

}

