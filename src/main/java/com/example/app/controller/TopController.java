package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/top")
public class TopController {
	@GetMapping
	public String showTopPage() {
		// "top" は src/main/resources/templates/top.html に対応
		return "top";
	}
}
