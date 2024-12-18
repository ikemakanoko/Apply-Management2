package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Todo;

@Mapper
public interface ToDoMapper {
	static List<Todo> selectAllTodos() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	} // 全件取得

	void insertTodo(Todo todo); // レコード挿入

	void updateTodo(Todo todo); // レコード更新

	void deleteTodo(int id); // レコード削除

	Todo findById(int id);

	void save(Todo task);

	void deleteById(int id);
}
