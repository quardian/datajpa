package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        Member member = new Member("이인호", 30);
        Member savedMember = memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findById(1L);
        assertThat(savedMember).isEqualTo(findMember.get());

    }

    @Test
    void basicCRUD(){

        System.out.println( "memberRepository=" + memberRepository.getClass() );

        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);

        // C 검증
        memberRepository.save(member1);
        memberRepository.save(member2);

        // R 검증
        Optional<Member> optFindMember1 = memberRepository.findById(member1.getId());
        Optional<Member> optFindMember2 = memberRepository.findById(member2.getId());

        assertThat(member1).isEqualTo( optFindMember1.get() );
        assertThat(member2).isEqualTo( optFindMember2.get() );

        // R 검증
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(2);

        // D 검증
        //memberJpaRepository.delete(member1);
        memberRepository.delete(member2);

        // U 검증
        member1.changeUserName("회원1");
        List<Member> aaa = memberRepository.findByUsernameAndAgeGreaterThan("member1", 5);
        assertThat(aaa.size()).isEqualTo(1);

        // R 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(1);

    }
}