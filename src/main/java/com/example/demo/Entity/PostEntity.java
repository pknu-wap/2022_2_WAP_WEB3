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
	@OneToMany(mappedBy = "Member")
	private Integer post_num;
	
	@Column(nullable=false, length = 30)		
	private String member_email;
	
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
			Integer post_num, String member_email, String theme, String location, 
			String content, LocalDateTime date, ImageEntity ImageId) {
		super();
		this.post_num = post_num;
		this.member_email = member_email;
		this.theme = theme;
		this.location = location;
		this.content = content;
		this.date = date;
		this.ImageId = ImageId;
	}
}