package com.ptit.user.run;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.ptit.user.entity")
@EnableJpaRepositories("com.ptit.user.repository")
@ComponentScan("com.ptit.user")
@SpringBootApplication
@EnableEurekaClient
public class UserBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserBackendApplication.class, args);
	}

}
