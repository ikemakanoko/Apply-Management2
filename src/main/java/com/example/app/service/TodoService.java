package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.domain.Todo;
import com.example.app.mapper.ToDoMapper;

@Service
public class TodoService {

	@Autowired
	private ToDoMapper toDoLIstMapper;

	public List<Todo> getTasksByExecuted() {
		return toDoLIstMapper.selectAllTodos();
	}

	public void addTask(String taskname) {
		Todo task = new Todo();
		task.setTaskname(taskname);
		task.setExecuted(0); // 初期値は未実行
		toDoLIstMapper.save(task);
	}

	public void markAsCompleted(int id) {
		Todo task = toDoLIstMapper.findById(id);
//		idに対応するタスクが存在すれば、そのタスクがtask変数に代入される
//		orElseThrow(); 例外をスローする
		task.setExecuted(1);
//		タスクのexecutedフィールドに１をセット
//		ここでexecutedはtinyint型のカラムであり、1は「実行済み」を意味する（0 は未実行）。
		toDoLIstMapper.save(task);
//		saveメソッドは、エンティティをデータベースに保存するためのJPARepository の標準メソッド。
//		既に存在するエンティティ（idが存在する）をsaveした場合、データベース上で更新が行われます。
//		もしidが存在しない場合、新規作成としてデータが挿入されます（今回は既に存在するタスクの更新なので、更新処理が走る）。
	}

	public void deleteTask(int id) {
		toDoLIstMapper.deleteById(id);
	}
}
