package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    @Rollback(false)
    @Test
    void testMember() {
        Member member = new Member("이인호");
        Member savedMember = memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findById(1L);
        assertThat(savedMember).isEqualTo(findMember.get());

    }

}