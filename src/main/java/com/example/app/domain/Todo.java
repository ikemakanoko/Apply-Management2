package com.example.app.domain;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Todo {
	
	private int id;
	
	@NotBlank
	private String taskname;
	
	//0 false
	//1 true
	@Column(name = "executed")
    private Integer executed; // true/false
	
	@CreationTimestamp
	private Timestamp createdAt;
	
	@UpdateTimestamp
	private Timestamp updatedAt;
}
