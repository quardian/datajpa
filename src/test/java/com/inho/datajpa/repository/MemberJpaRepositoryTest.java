package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    void testMember() {
        Member member = new Member("Name1", 20);
        Member savedMember = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.find(1L);

        assertThat(savedMember).isEqualTo(findMember);

    }

    @Test
    void basicCRUD(){

        System.out.println( "memberJpaRepository=" + memberJpaRepository.getClass() );

        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);

        // C 검증
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        // R 검증
        Optional<Member> optFindMember1 = memberJpaRepository.findById(member1.getId());
        Optional<Member> optFindMember2 = memberJpaRepository.findById(member2.getId());

        assertThat(member1).isEqualTo( optFindMember1.get() );
        assertThat(member2).isEqualTo( optFindMember2.get() );

        // R 검증
        List<Member> members = memberJpaRepository.findAll();
        assertThat(members.size()).isEqualTo(2);

        // D 검증
        //memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);

        // U 검증
        member1.changeUserName("회원1");

        // R 검증
        long count = memberJpaRepository.count();
        assertThat(count).isEqualTo(1);

    }

}