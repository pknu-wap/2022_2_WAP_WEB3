package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class member {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	
	private String pw;
	private String pw2;
	private String name;
	private String club;
}
