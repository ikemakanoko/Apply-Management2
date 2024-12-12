package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.Todo;
import com.example.app.mapper.ToDoMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskController {

	private final ToDoMapper toDoMapper;
	List<Todo> todos = new ArrayList<>();

	//一覧
    @GetMapping("/todoList")
    public String showTodoList(Model model) {
        // モデルにToDoリストを追加
    	List<Todo> todo = toDoMapper.selectAll();
//    	Todo todos = new Todo();
        model.addAttribute("todos", todos);
        model.addAttribute("todo", todo);
        model.addAttribute("todo", new Todo());
        return "todoList";
    }

  	//新規登録
    @GetMapping("/todoList/todoAdd")
    public String addTodo(@ModelAttribute Todo todo, Model model) {
        // 新しいToDoをリストに追加
        // リダイレクトしてリストを更新
    	List<Todo> todos = toDoMapper.selectAll();
    	model.addAttribute("todos", todos);
    	model.addAttribute("todo", new Todo());
    	model.addAttribute("taskname", todo);
    	System.out.println(todos);
        return "todoList/todoAdd";
    }
    
	//エラーハンドリング、バリデーション(新規追加)
	@PostMapping("/todoList")
	public String addPost(
			@Valid Todo todo,
			Errors errors,
			Model model) {
		System.out.println("エラー数:　" + errors.getErrorCount());

		// エラーの詳細を調べる
		errors.getAllErrors().forEach(System.out::println);

		// エラーがある場合、元のフォームに戻す
		if (errors.hasErrors()) {
			model.addAttribute("todo", todo); // 入力された値を維持
			return "todoList";
		}
		//System.out.println(appliedCompanyList);
		// バリデーションが通った場合はデータを保存
		toDoMapper.add(todo);
		return "redirect:/todoList"; // 完了ページに遷移
	}	
}