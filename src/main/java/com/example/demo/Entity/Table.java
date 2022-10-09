package com.example.demo.Entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
public class Table {
	private Table table;
	@Column( name="table_id", nullable=false, length=50 )
    private String id;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer number;
    
    @Column(name="table_title", nullable=false, length = 50)
    private String title;
    
    @Column(name="table_content", nullable=false, columnDefinition = "TEXT")
    private String content;
    
    @Column(name="Member_date")
    private LocalDateTime  date;
}	
