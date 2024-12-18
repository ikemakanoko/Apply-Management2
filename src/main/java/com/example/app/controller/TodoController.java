package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.Todo;
import com.example.app.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping("/todoList")
	public String showTodoList(Model model) {
		// 未実行タスクと実行済みタスクを取得
		List<Todo> pendingTasks = todoService.getTasksByExecuted();
		List<Todo> completedTasks = todoService.getTasksByExecuted();

		model.addAttribute("pendingTasks", pendingTasks);
		model.addAttribute("completedTasks", completedTasks);

		return "todoList";
	}

	@PostMapping("/addTodo")
	public String addTodo(@RequestParam String taskname) {
		todoService.addTask(taskname);
		return "redirect:/";
	}

	@PostMapping("/updateTodo/{id}")
	public String updateTodo(@PathVariable int id) {
		todoService.markAsCompleted(id);
		return "redirect:/";
	}

	@GetMapping("/deleteTodo/{id}")
	public String deleteTodo(@PathVariable int id) {
		todoService.deleteTask(id);
		return "redirect:/";
	}
}
