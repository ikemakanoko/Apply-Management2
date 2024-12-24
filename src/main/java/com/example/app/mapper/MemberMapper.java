package com.example.app.mapper;

import java.util.List;

import com.example.app.domain.Member;
//ログインページ用
public interface MemberMapper {
    List<Member> selectMembers();
}
