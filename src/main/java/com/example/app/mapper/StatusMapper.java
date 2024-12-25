package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.StatusList;

@Mapper
public interface StatusMapper {

	//一覧を取得
	List<StatusList> selectAll();
}
