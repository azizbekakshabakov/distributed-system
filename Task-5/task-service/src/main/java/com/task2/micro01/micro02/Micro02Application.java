package com.task2.micro01.micro02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Micro02Application {

	public static void main(String[] args) {
		SpringApplication.run(Micro02Application.class, args);
	}

}
