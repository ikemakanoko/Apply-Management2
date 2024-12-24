package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.Todo;
import com.example.app.mapper.ToDoMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class TodoController {

	private final ToDoMapper toDoMapper;

	@GetMapping("/todoList")
	public String showTodoList(Model model) {
		// 未実行タスクと実行済みタスクを取得
		List<Todo> todos = toDoMapper.selectAllTodos();
		List<Todo> pendingTasks = toDoMapper.getTasksByExecute();
		List<Todo> completedTasks = toDoMapper.getTasksByExecuted();

		model.addAttribute("todos", toDoMapper.selectAllTodos());
		model.addAttribute("todoss", todos);
		//completedTasks(実行済みタスク)　pendingTasks(未実行タスク)
		model.addAttribute("pendingTasks", pendingTasks);
		model.addAttribute("completedTasks", completedTasks);

		return "todoList";
	}

	@GetMapping("/deleteTodo/{id}")
	public String deleteTodo(@PathVariable int id) {
		toDoMapper.deleteById(id);
		return "redirect:/todoList";
	}

	//	public List<Todo> getTasksByExecuted() {
	//		return toDoMapper.selectAllTodos();
	//	}

	@GetMapping("/addTodo")
	public String addTodo(Model model) {
		model.addAttribute("todo", new Todo());
//		Todo todo = new Todo();
//		todo.setTaskname(taskname);
//		Todo todo2 = new Todo();
//		todo2.setExecuted(0); // 初期値は未実行
//		toDoMapper.save(null);
		return "addTodo";
	}

	//エラーハンドリング、バリデーション(新規追加)
	@PostMapping("/addTodo")
	public String addTodo(
			@Valid Todo todo,
			Errors errors,
			Model model) {
		System.out.println("エラー数:　" + errors.getErrorCount());

		// エラーの詳細を調べる
		errors.getAllErrors().forEach(System.out::println);

		// エラーがある場合、元のフォームに戻す
		if (errors.hasErrors()) {
			model.addAttribute("todos", todo); // 入力された値を維持
			System.out.println(errors);
			return "addTodo";
		}
		//System.out.println(appliedCompanyList);
		// バリデーションが通った場合はデータを保存
		toDoMapper.addTodo(todo);
		return "redirect:/todoList"; // 完了ページに遷移
	}
	
	//実行済みボタンを押したら、SQL、getTasksByExecutedが実行されるようにする
	//SQL,getTasksByExecutedを下に表示できるようにする
	//completedTasks(実行済みタスク)　pendingTasks(未実行タスク)
	
	@GetMapping("/updateTodo/{id}")
	public String markAsCompleted(Model model) {
		model.addAttribute("todo", new Todo());
//		Todo todo = new Todo();
//		todo.setTaskname(taskname);
//		Todo todo2 = new Todo();
//		todo2.setExecuted(0); // 初期値は未実行
//		toDoMapper.save(null);
		return "updateTodo";
	}
	
	@PostMapping("/updateTodo/{id}")
	public String markAsCompleted(@PathVariable int id) {
	    // `id`を用いてタスクを実行済みに更新
	    toDoMapper.markAsCompleted(id);
	    return "redirect:/todoList"; // 更新後にタスクリストページへリダイレクト
	}

}
