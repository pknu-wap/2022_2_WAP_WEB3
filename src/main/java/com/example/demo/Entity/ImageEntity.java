package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity(name = "image")
public class ImageEntity {
	@Id
	@Column(length = 36)
	@JoinColumn(name="Member") 
	private String ImageId;
	
	@Column(length = 100)
	private String FileSavedName;
	
	@Column(length = 100)
	private String FilePath;
	
	public ImageEntity() {
		super();
	}
	
	@Builder
	public ImageEntity(String ImageId, String FileSavedName, String FilePath) {
		this.ImageId = ImageId;
		this.FileSavedName = FileSavedName;
		this.FilePath = FilePath;
	}
	
}
