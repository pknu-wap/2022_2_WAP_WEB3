package com.example.demo.model.dto;

import java.time.LocalDateTime;

import com.example.demo.Entity.PostEntity;

public class PostDTO {
	private Integer post_num;
	private String member_email;
	private String theme;
	private String location;
	private String infoamtion;
	private LocalDateTime date;
	public PostDTO() {}
	
	public static class Builder {
		private Integer post_num;
		private String member_email;
		private String theme;
		private String location;
		private String infoamtion;
		private LocalDateTime date;
		public Builder() {}
		
		public Builder setPostNum(Integer post_num) {
			this.post_num = post_num;
			return this;
		}
		public Builder setMemberEmail(String member_email) {
			this.member_email = member_email;
			return this;
		}
		public Builder setTheme(String theme) {
			this.theme = theme;
			return this;
		}
		public Builder setLocation (String location) {
			this.location = location;
			return this;
		} 
		
		public Builder setContent (String infoamtion) {
			this.infoamtion = infoamtion;
			return this;
		} 
		
		public Builder setDate (LocalDateTime date) {
			this.date = date;
			return this;
		} 
		
		public PostDTO build() {
			PostDTO rdto = new PostDTO();
			rdto.post_num = post_num;
			rdto.member_email = member_email;
			rdto.theme = theme;
			rdto.location = location;
			rdto.infoamtion = infoamtion;
			rdto.date = date;
			
			return rdto;
		}
	}

	public Integer getPostNum() {
		return post_num;
	}

	public String getMemberEmail() {
		return member_email;
	}

	public String getTheme() {
		return theme;
	}

	public String getLocation() {
		return location;
	}

	public String getInfoamtion() {
		return infoamtion;
	}

	public LocalDateTime getDate() {
		return date;
	}
}
