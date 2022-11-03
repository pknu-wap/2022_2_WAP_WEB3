package com.example.demo;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class BuskerBuskerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuskerBuskerApplication.class, args);
		
	}

}
