package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.AppliedCompanyList;

import jakarta.validation.Valid;

@Mapper
public interface AppliedCompanyMapper {
	//一覧を取得
	List<AppliedCompanyList> selectAll();

	//個別
	AppliedCompanyList selectById(Integer id);

	//登録
	void add(AppliedCompanyList appliedCompanyList);

	//削除
	void deleteById(int id);

	//更新
	void update(@Valid AppliedCompanyList appliedCompanyList);
}
