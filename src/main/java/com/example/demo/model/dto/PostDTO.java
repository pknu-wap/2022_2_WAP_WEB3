package com.example.demo.model.dto;

import java.time.LocalDateTime;

import com.example.demo.Entity.PostEntity;

public class PostDTO {
	private Integer post_num;
	private String member_email;
	private String theme;
	private String location;
	private String content;
	private LocalDateTime date;
	private String image_id;
	public PostDTO() {}
	
	public static class Builder {
		private Integer post_num;
		private String member_email;
		private String theme;
		private String location;
		private String content;
		private LocalDateTime date;
		private String image_id;
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
		
		public Builder setContent (String content) {
			this.content = content;
			return this;
		} 
		
		public Builder setDate (LocalDateTime date) {
			this.date = date;
			return this;
		} 
		
		public Builder setImageId (String image_id) {
			this.image_id = image_id;
			return this;
		} 
		
		public PostDTO build() {
			PostDTO rdto = new PostDTO();
			rdto.post_num = post_num;
			rdto.member_email = member_email;
			rdto.theme = theme;
			rdto.location = location;
			rdto.content = content;
			rdto.date = date;
			rdto.image_id = image_id;
			
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

	public String getContent() {
		return content;
	}

	public LocalDateTime getDate() {
		return date;
	}
	
	public String getImageId() {
		return image_id;
	}
}
