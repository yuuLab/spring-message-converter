package com.yuulab.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.yuuLab" })
public class SpringApiConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringApiConverterApplication.class, args);
	}

}
