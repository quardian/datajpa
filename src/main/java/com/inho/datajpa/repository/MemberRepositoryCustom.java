package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
