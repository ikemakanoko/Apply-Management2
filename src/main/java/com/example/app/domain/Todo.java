package com.example.app.domain;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Todo {
	private int id;
	
	@NotBlank
	private String taskName;
	
	//0 false
	//1 true
	private Integer executed;
	
	@CreationTimestamp
	private Timestamp createdAt;
	
	@UpdateTimestamp
	private Timestamp updatedAt;
}
