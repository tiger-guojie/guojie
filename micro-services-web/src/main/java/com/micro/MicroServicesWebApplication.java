package com.micro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.micro.mapper")
public class MicroServicesWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicesWebApplication.class, args);
	}
}
