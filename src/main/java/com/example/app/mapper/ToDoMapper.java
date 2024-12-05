package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Todo;
@Mapper

public interface ToDoMapper {
	public List<Todo> selectAll();
	Todo selectById(Integer id);
	void add(Todo todo);
	void update(Todo todo);
	void deleteById(Integer id);
}
