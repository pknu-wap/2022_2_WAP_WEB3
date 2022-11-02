package com.example.demo.model.dto;

import java.time.LocalDateTime;

import com.example.demo.Entity.ReservationEntity;

public class ReservationDTO {
	private Integer num;
	private String location;
	private String content;
	private LocalDateTime date;
	public ReservationDTO() {}
	public ReservationDTO(String location, String content, LocalDateTime date) {
		this.location = location;
		this.content = content;
		this.date = date;
	}
	
	public static class Builder {
		private String location;
		private String content;
		private LocalDateTime date;
		public Builder() {}
		
		public Builder setLocation (String location) {
			this.location = location;
			return this;
		} 
		
		public Builder setContent (String contnet) {
			this.content = content;
			return this;
		} 
		
		public Builder setDate (LocalDateTime date) {
			this.date = date;
			return this;
		} 
		
		public ReservationDTO build() {
			ReservationDTO rdto = new ReservationDTO();
			rdto.location = location;
			rdto.content = content;
			rdto.date = date;
			
			return rdto;
		}
	}

	public Integer getNum() {
		return num;
	}

	public String getLocation() {
		return location;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getContnet() {
		return content;
	}
}
