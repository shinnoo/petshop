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

@Data
@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id",unique = true,nullable = false)
    private String id;

    @Column(name = "name",columnDefinition = "nvarchar",length = 1024)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "local",columnDefinition = "nvarchar",length = 2048)
    private String local;

    @Column(name = "phone")
    private String phone;
}
