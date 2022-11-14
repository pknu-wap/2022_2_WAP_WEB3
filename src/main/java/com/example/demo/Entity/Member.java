package com.example.demo.Entity;


import javax.persistence.*;

import com.example.demo.model.dto.MemberDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


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