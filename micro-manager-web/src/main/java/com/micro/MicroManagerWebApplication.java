package com.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement  // 启注解事务管理，等同于xml配置方式的
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MicroManagerWebApplication  extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MicroManagerWebApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(MicroManagerWebApplication.class, args);
	}
}