package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Todo;

@Mapper
public interface ToDoMapper {
	List<Todo> selectAllTodos();

	void insertTodo(Todo todo); // レコード挿入

	void updateTodo(Todo todo); // レコード更新

	void deleteTodo(int id); // レコード削除

	Todo findById(int id);

	void deleteById(int id);
	
	@Insert("INSERT INTO todo (taskname, executed, created_at) VALUES (#{taskname}, #{executed}, #{createdAt})")
    void save(Todo todo);
}
