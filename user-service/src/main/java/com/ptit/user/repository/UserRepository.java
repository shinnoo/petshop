package com.ptit.user.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ptit.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {}