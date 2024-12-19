package com.example.app.mapper;

import java.util.List;

import com.example.app.domain.Member;

public interface MemberMapper {
    List<Member> selectMembers();
}
