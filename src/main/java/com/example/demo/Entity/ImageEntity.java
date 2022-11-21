package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity(name = "image")
public class ImageEntity {
	@Id
	@Column(length = 36)
	private String ImageId;
	
	@Column(length = 50)
	private String FileOriginName;
	
	@Column(length = 50)
	private String FilePath;
	
	public ImageEntity() {
		super();
	}
	
	@Builder
	public ImageEntity(String ImageId, String FileOriginName, String FilePath) {
		this.ImageId = ImageId;
		this.FileOriginName = FileOriginName;
		this.FilePath = FilePath;
	}
	
}
