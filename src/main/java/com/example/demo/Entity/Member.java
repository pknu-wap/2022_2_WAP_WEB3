package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
	@Id
	private Integer idx;
	
//	@OneToMany(fetch=FetchType.LAZY)
//	@JoinColumn(name="Board_id")
	@Column(nullable=false, length=50 )
	private String id;	
	
	@Column(length=12)
	private String pw;
	
	@Column(length=12)
	private String pw2;
	
	@Column(length=5)
	private String name;
	
	private String club;

	public void setId(String id) {
		this.id = id;
		
	}
}
