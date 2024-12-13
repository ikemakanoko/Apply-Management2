package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	//一覧
    @GetMapping("/todoList")
    public String list(Model model) {
        // モデルにToDoリストを追加
    	List<Todo> todoList = toDoMapper.selectAll();
    	System.out.println("todoList->"+todoList);
        model.addAttribute("todoList", todoList);
        model.addAttribute("todo", new Todo());
        //単一のTodoオブジェクトを渡す
        //一覧表示
        return "todoList";
    }
    
    //新規登録
    @GetMapping("/todoAdd")
    public String addTodo(@ModelAttribute Todo todo, Model model) {
    	// 新しいToDoをリストに追加
    	// リダイレクトしてリストを更新
    	model.addAttribute("todo", new Todo());
    	System.out.println(todo);
    	return "todoAdd";
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
	
	@GetMapping("/deleteTodo/{id}")
	public String deleteCompany(@PathVariable("id") Integer id,
			Model model
			) {
		model.addAttribute("todo", new Todo());
		toDoMapper.deleteById(id); // idで削除
		// idを使って削除処理を実行
		System.out.println("削除するタスクのID: " + id);
		// 必要な削除処理をここで行う
		return "todoList"; // 削除後のリダイレクト先
	}
	
}