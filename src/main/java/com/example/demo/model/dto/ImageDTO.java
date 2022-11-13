package com.example.demo.model.dto;

import java.awt.Image;

public class ImageDTO {
	private String image;
	private String imageName;
	
	public ImageDTO(String image, String imageName) {
		this.image = image;
		this.imageName = imageName;
	}
	
	public ImageDTO(String imageName) {
		this.imageName = imageName;
	}
	
	public String getImage() {
		return image;
	}
	public String getImagName() {
		return imageName;
	}
}
