package com.example.demo.Entity;

import java.time.LocalDateTime;
import java.util.List;

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
	
	@Column(nullable=false, length = 30)		//나중에 nullable=false로 바꿔야함 + 테이블 삭제 했다가 다시
	private String member_email;
	
	@Column(nullable=false, length = 20) 	//나중에 nullable=false로 바꿔야함
	private String theme;
	
	@Column(nullable=false, length = 50)
	private String location;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime date;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="imageId") 
	private ImageEntity image;

	@Builder
	public PostEntity(
			Integer post_num, String member_email, String theme, String location, 
			String content, LocalDateTime date, ImageEntity image) {
		super();
		this.post_num = post_num;
		this.member_email = member_email;
		this.theme = theme;
		this.location = location;
		this.content = content;
		this.date = date;
		this.image = image;
	}
}