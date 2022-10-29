package com.example.demo.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Getter
@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer num;
	
	@Column(nullable=false, length = 50)
	private String location;
	
	private LocalDateTime date;

	@Column(columnDefinition = "TEXT")
	private String contnet;
	
	public Reservation(String location, LocalDateTime date, String content) {
		this.location = location;
		this.date = date;
		this.contnet = content;
	}
	
	
	
	
}
