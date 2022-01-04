package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    void testMember() {
        Member member = new Member("Name1");
        Member savedMember = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.find(1L);

        assertThat(savedMember).isEqualTo(findMember);

    }

}