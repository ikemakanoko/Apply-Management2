//package com.example.app.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//
//	@GetMapping("/login")
//	public String loginGet(Model model) {
//		model.addAttribute("loginMember", new Member());
//		return "login";
//	}
//
//	@PostMapping("/login")
//	public String loginPost(
//			@Validated(LoginGroup.class) @ModelAttribute("loginMember") Member loginMember,
//			Errors errors) {
//		if (errors.hasErrors()) {
//			return "login";
//		}
//		// 未入力でない場合、ID とパスワードをチェック
//		if (!loginMember.getEmail().equals("taro@example.com")
//				|| !loginMember.getPassword().equals("abcd")) {
//			// グローバルエラーの追加
//			errors.reject("error.wrong_id_or_password");
//			return "login";
//		}
//		return "loginDone";
//	}
//}
