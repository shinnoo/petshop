package io.github.tubean.eureka.gallery.repository;

import io.github.tubean.eureka.gallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
