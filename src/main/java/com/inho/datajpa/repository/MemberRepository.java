package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findMyUsername(String username);
}
