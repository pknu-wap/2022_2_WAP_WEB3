package com.example.demo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entity.Reservation;
import com.example.demo.Repository.ReservationRepository;

@SpringBootTest
class BuskerBuskerApplicationTests {

	@Autowired
	private ReservationRepository rr;

	@Test
	void test() {
		Reservation r = new Reservation("test location", LocalDateTime.now(), "test content");
		this.rr.save(r);
	}

}
