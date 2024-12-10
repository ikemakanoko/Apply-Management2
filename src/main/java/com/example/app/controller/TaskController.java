package com.example.app.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
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
	private Collection<Todo> allTodos;

	//一覧
	@GetMapping("/toDolist")
	public String list(Model model) {
		//一覧取得
		List<Todo> todoLists = toDoMapper.selectAll();
		// 未実行タスク (executed == 0)
		List<Todo> pendingTodos = allTodos.stream()
				.filter(todo -> todo.getExecuted() == 0) // 0なら未実行
				.toList();

		// 実行済みタスク (executed == 1)
		List<Todo> completedTodos = allTodos.stream()
				.filter(todo -> todo.getExecuted() == 1) // 1なら実行済み
				.toList();

		model.addAttribute("pendingTodos", pendingTodos);
		model.addAttribute("completedTodos", completedTodos);

		// 新規タスクのフォーム用オブジェクトを追加
		model.addAttribute("todo", new Todo());

		return "toDolist";
	}

	public String addGet(Todo todo, Model model) {
		//ToDoList新規登録　タスクの追加を押したら新規登録できる仕様にしたい
		//新規登録したオブジェクトを、HTMLの未実行タスクのところに移動させる
		model.addAttribute("todo", new Todo());
		toDoMapper.add(todo);
		return "toDolist";
	}

	public String showAddDonePage(Model model) {
		Todo task = new Todo(); // Todoオブジェクトを作成または取得
		task.setTaskName("Task example"); // taskNameに値を設定
		model.addAttribute("task", task); // モデルにtaskを追加
		return "toDolist"; // adddone.htmlを返す
	}

	//エラーハンドリング、バリデーション(新規追加)
	@PostMapping("/toDolist")
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
			return "toDolist";
		}
		//System.out.println(appliedCompanyList);
		// バリデーションが通った場合はデータを保存
		toDoMapper.add(todo);
		return "redirect:/toDolist"; // 完了ページに遷移
	}

	//	@GetMapping("/adddone")
	//	public String addDone() {
	//		return "adddone"; // adddone.htmlページに遷移
	//	}

	//	// 編集処理
	//	@GetMapping("/update")
	//	public String updateGet(@PathVariable("id") Integer id, Model model) {
	//		Todo todo = toDoMapper.selectById(id);
	//		model.addAttribute("todo", todo);
	//		return "update"; // ビュー名
	//
	//	}

}
