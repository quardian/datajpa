package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
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