package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entity.ReservationEntity;
import com.example.demo.Repository.ReservationRepository;
import com.example.demo.Service.ReservationService;

@SpringBootTest
@AutoConfiguration
class BuskerBuskerApplicationTests {

	@Autowired
	private ReservationRepository rr;
	
	@Autowired
	private ReservationService rs;

	@Test
	void test() {
		
	}

}
