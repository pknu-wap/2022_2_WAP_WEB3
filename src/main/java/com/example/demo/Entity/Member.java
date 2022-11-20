package com.example.demo.Entity;


import javax.persistence.*;

import com.example.demo.model.dto.MemberDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id")
	private MemberProfile memberProfile;

	@Builder
	public Member(String email, String password, MemberRole role, MemberProfile memberProfile) {
		this.email = email;
		this.password = password;
		this.role = role;
		this.memberProfile = MemberProfile.builder().build();
	}

	public static Member createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
		return Member.builder()
				.email(memberDTO.getEmail())
				.password(passwordEncoder.encode(memberDTO.getPassword()))
				.role(MemberRole.USER)
				.build();
	}
}