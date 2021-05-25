package com.ptit.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    private String id;


    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;
    
    @Column(name = "address")
    private String address;

    @Column(name = "image")
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

  public void setEmail(String email) {
        this.email = email;
    }
  public String getAddress() {
	      return address;
	  }
	
  public void setAddress(String address) {
	      this.address = address;
	  }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}