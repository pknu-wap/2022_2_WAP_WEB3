package com.example.demo.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.model.dto.ReservationDTO;

import groovy.transform.builder.Builder;
import lombok.Getter;

@Getter
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

	@Builder
	public ReservationEntity(String location, LocalDateTime date, String content) {
		super();
		this.location = location;
		this.date = date;
		this.content = content;
	}
	
	public ReservationEntity(ReservationDTO dto) {
		super();
		this.location = dto.getLocation();
		this.date = dto.getDate();
		this.content = dto.getContnet();
	}
}
