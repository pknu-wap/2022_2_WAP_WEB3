package com.example.demo.Entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "post")
@NoArgsConstructor
public class PostEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer post_num;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(nullable=false, name="email") 
//	private Member email;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=true, length = 20) 
	private String theme;
	
	@Column(nullable=false, length = 50)
	private String location;
	
	@Column(nullable=false, columnDefinition = "TEXT")
	private String content;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime date;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable=true, name="ImageId") 
	private ImageEntity ImageId;

	@Builder
	public PostEntity(
			Integer post_num, String email, String theme, String location, 
			String content, LocalDateTime date, ImageEntity ImageId) {
		super();
		this.post_num = post_num;
		this.email = email;
		this.theme = theme;
		this.location = location;
		this.content = content;
		this.date = date;
		this.ImageId = ImageId;
	}
}