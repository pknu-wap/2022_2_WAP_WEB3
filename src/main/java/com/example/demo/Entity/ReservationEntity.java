package com.example.demo.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import groovy.transform.builder.Builder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "reservation")
@NoArgsConstructor
public class ReservationEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer num;
	
	@Column(nullable=false, length = 50)
	private String location;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime date;

	@Column(columnDefinition = "TEXT")
	private String content;

	public ReservationEntity(String location, String content, LocalDateTime date) {
		super();
		this.location = location;
		this.content = content;
		this.date = date;
	}
}
