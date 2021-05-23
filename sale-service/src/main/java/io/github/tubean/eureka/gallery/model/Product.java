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
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Table(name = "product")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id",unique = true,nullable = false)
    private String id;

    @Column(name = "name",columnDefinition = "nvarchar",length = 1024)
    private String name;

    @Column(name = "type",columnDefinition = "nvarchar")
    private String type;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "price")
    private Float price;

    @Column(name = "code")
    private String code;

    @Column(name = "total_quantity")
    private Float totalQuantity;

    @Column(name = "description",columnDefinition = "nvarchar", length = 1024)
    private String description;

}
