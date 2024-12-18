package com.example.app.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
@Data
public class AppliedCompanyList {
	
	//番号をオートインクリメントで
	private Integer id;
	
	@NotNull
	private Integer statusid;
	
	private String  status;
	
	//会社名
	@NotBlank
	private String name;
	
	//創立年
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate established;

	//社長
	private String president;

	//説明・めも
	@NotBlank
	private String detail;

	//給料
	@NotNull
	private Integer salaly;
	
	//労働時間
	@NotNull
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime workingTime;

	@NotNull
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime workingTime2;
	
	//どこから応募
	@NotBlank
	private String applied;
	
	//nullでもOK
	private String url;
	
	
}
