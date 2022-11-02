package com.example.demo.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import groovy.transform.builder.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity(name = "reservation")
public class ReservationEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer num;
	
	@Column(nullable=false, length = 50)
	private String location;
	
	private LocalDateTime date;

	@Column(columnDefinition = "TEXT")
	private String content;
}
