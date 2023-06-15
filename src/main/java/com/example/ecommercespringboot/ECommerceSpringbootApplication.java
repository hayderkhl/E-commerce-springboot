package com.example.ecommercespringboot;

import com.example.ecommercespringboot.service.serviceImpl.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.File;

@SpringBootApplication
public class ECommerceSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceSpringbootApplication.class, args);
	}
}
