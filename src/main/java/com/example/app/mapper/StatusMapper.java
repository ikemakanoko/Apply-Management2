package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.StatusList;

import jakarta.validation.Valid;

@Mapper
public interface StatusMapper {

	//一覧を取得
	List<StatusList> selectAll();

	//個別
	StatusList selectById(Integer id);

	//登録
	void add(StatusList statusList);

	//削除
	void deleteById(int id);

	//更新
	void update(@Valid StatusList statusList);
}
