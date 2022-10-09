package com.example.demo.Entity;

import java.lang.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Member {
	@Id
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="table_id")
	private java.lang.String id;	
	
	@Column(name="Member_pw", length=12)
	private String pw;
	
	@Column(name="Member_pw2", length=12)
	private String pw2;
	
	@Column(name="Member_name", length=5)
	private String name;
	
	@Column(name="Member_club")
	private String club;

	public void setId(java.lang.String id) {
		this.id = id;
		
	}
}
