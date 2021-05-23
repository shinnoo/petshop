package io.github.tubean.eureka.gallery.common.constant;

import io.github.tubean.eureka.gallery.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResultPage<T> {
    private long totalItems;
    private List<T> pageList;

}
