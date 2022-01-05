package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import com.inho.datajpa.web.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
        final int limit = 3;
        int requestPage = 1;

        // Spring Data JPA 는 페이지를 0번 부터 시작함
        Sort.Order usernameDesc = Sort.Order.desc("username");
        Sort.Order idAsc = Sort.Order.asc("id");

        PageRequest pageable = PageRequest.of((requestPage-1), limit,  Sort.by(usernameDesc, idAsc));

        Page<Member> page    = memberRepository.findPageByAge(age, pageable);

        long totalElements      = page.getTotalElements();
        List<Member> members    = page.getContent();
        int totalPages          = page.getTotalPages();
        int number              = page.getNumber() + 1;
        boolean isFirst         = page.isFirst();
        boolean isLast          = page.isLast();
        boolean hasNext         = page.hasNext();

        System.out.println("totalElements = " + totalElements);
        System.out.println("pageItemCount = " + members.size());
        System.out.println("totalPages = " + totalPages);
        System.out.println("number = " + number);
        System.out.println("isFirst = " + isFirst);
        System.out.println("isLast = " + isLast);
        System.out.println("hasNext = " + hasNext);
    }

    @Test
    void slice()
    {
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 10);
        Member member3 = new Member("member3", 10);
        Member member4 = new Member("member4", 10);
        Member member5 = new Member("member5", 10);

        memberRepository.saveAll(Arrays.asList(member1, member2, member3, member4, member5));

        int age = 10;
        final int limit = 3;
        int requestPage = 2;

        // Spring Data JPA 는 페이지를 0번 부터 시작함
        Sort.Order usernameDesc = Sort.Order.desc("username");
        Sort.Order idAsc = Sort.Order.asc("id");

        PageRequest pageable = PageRequest.of((requestPage-1), limit,  Sort.by(usernameDesc, idAsc));

        Slice<Member> page    = memberRepository.findSliceByAge(age, pageable);

        //long totalElements      = page.getTotalElements();
        List<Member> members    = page.getContent();
        //int totalPages          = page.getTotalPages();
        int number              = page.getNumber() + 1;
        boolean isFirst         = page.isFirst();
        boolean isLast          = page.isLast();
        boolean hasNext         = page.hasNext();

        System.out.println("pageItemCount = " + members.size());
        System.out.println("number = " + number);
        System.out.println("isFirst = " + isFirst);
        System.out.println("isLast = " + isLast);
        System.out.println("hasNext = " + hasNext);


    }

    @Test
    void customQueryPaging()
    {
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 10);
        Member member3 = new Member("member3", 10);
        Member member4 = new Member("member4", 10);
        Member member5 = new Member("member5", 10);

        memberRepository.saveAll(Arrays.asList(member1, member2, member3, member4, member5));

        int age = 10;
        final int limit = 3;
        int requestPage = 1;

        // Spring Data JPA 는 페이지를 0번 부터 시작함
        Sort.Order usernameDesc = Sort.Order.desc("username");
        Sort.Order idAsc = Sort.Order.asc("id");

        PageRequest pageable = PageRequest.of((requestPage-1), limit,  Sort.by(usernameDesc, idAsc));

        Page<Member> page    = memberRepository.findCustomPageByAge(age, pageable);

        // Entity to Dto convert
        Page<MemberDto> map = page.map(m -> new MemberDto(m.getId(), m.getUsername(), m.getAge(), null));
        System.out.println("map = " + map);

        long totalElements      = page.getTotalElements();
        List<Member> members    = page.getContent();
        int totalPages          = page.getTotalPages();
        int number              = page.getNumber() + 1;
        boolean isFirst         = page.isFirst();
        boolean isLast          = page.isLast();
        boolean hasNext         = page.hasNext();

        System.out.println("totalElements = " + totalElements);
        System.out.println("pageItemCount = " + members.size());
        System.out.println("totalPages = " + totalPages);
        System.out.println("number = " + number);
        System.out.println("isFirst = " + isFirst);
        System.out.println("isLast = " + isLast);
        System.out.println("hasNext = " + hasNext);
    }


}