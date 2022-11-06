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
@Entity(name = "post")
@NoArgsConstructor
public class PostEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer post_num;
	
	@Column(nullable=false, length = 30)
	private String member_email;
	
	@Column(nullable=false, length = 20)
	private String theme;
	
	@Column(nullable=false, length = 50)
	private String location;
	
	@Column(columnDefinition = "TEXT")
	private String infomation;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime date;


	public PostEntity(
			String member_email, String theme,String location, String infomation, LocalDateTime date) {
		super();
		this.member_email = member_email;
		this.theme = theme;
		this.location = location;
		this.infomation = infomation;
		this.date = date;
	}
}
