package com.example.demo.Entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.dto.MemberDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Table(name = "member")
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(unique = true)
	private String email;

	private String password;

	@Enumerated(EnumType.STRING)
	private MemberRole role;
	
	@OneToMany
    @JoinColumn(name = "post_num")
	private List<PostEntity> list; 

	@Builder
	public Member(String email, String password, MemberRole role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public static Member createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
		Member member = Member.builder()
				.email(memberDTO.getEmail())
				.password(passwordEncoder.encode(memberDTO.getPassword()))
				.role(MemberRole.USER)
				.build();
		return member;
	}
}