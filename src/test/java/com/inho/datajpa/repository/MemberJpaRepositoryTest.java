package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberRepository;

    @Test
    void testMember() {
        Member member = new Member("Name1", 20);
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.find(1L);

        assertThat(savedMember).isEqualTo(findMember);

    }

    @Test
    void basicCRUD(){

        System.out.println( "memberJpaRepository=" + memberRepository.getClass() );

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

        List<Member> aaa = memberRepository.findByUsernameAndAgeGreaterThan("회원1", 5);
        assertThat(aaa.size()).isEqualTo(1);

        // R 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(1);

    }

    @Test
    void paging()
    {
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 10);
        Member member3 = new Member("member3", 10);
        Member member4 = new Member("member4", 10);
        Member member5 = new Member("member5", 10);

        memberRepository.saveAll(Arrays.asList(member1, member2, member3, member4, member5));

        int age = 10;
        int page = 2;
        long totalPage = 0;
        long totalCount = 0;
        final int limit = 3;
        final int offset = (page -1)*limit;

        List<Member> members = memberRepository.findByPage(age, offset, limit);
        totalCount = memberRepository.totalCount(age);
        totalPage = (totalCount / limit) + (( (totalCount % limit) > 0 )  ? 1L : 0L);

        System.out.println("pageItemCount = " + members.size());
        System.out.println("(page=" + page + "/totalPage=" + totalPage + "), totalCount=" + totalCount);
    }
}