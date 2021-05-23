package io.github.tubean.eureka.gallery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Table(name = "orders")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Orders {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id",unique = true,nullable = false)
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "status")
    private String status;

    @Column(name = "total_price")
    private Long totalPrice;
}
