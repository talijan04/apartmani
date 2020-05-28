package com.talijan04.testiranje.apartmani;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/*
@ComponentScan(basePackages = {"com.talijan04.testiranje.apartmani.api"})
@ComponentScan(basePackages = {"com.talijan04.testiranje.apartmani.config"})
@ComponentScan(basePackages = {"com.talijan04.testiranje.apartmani.dao"})
@ComponentScan(basePackages = {"com.talijan04.testiranje.apartmani.datasource"})
@ComponentScan(basePackages = {"com.talijan04.testiranje.apartmani.model"})
@ComponentScan(basePackages = {"com.talijan04.testiranje.apartmani.service"})
*/
public class ApartmaniApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApartmaniApplication.class, args);
	}

}
