package com.inho.datajpa.web.controller;

import com.inho.datajpa.entity.Member;
import com.inho.datajpa.repository.MemberRepository;
import com.inho.datajpa.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id){
        log.info("####findMember###### id={}", id);
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member){
        log.info("####findMember2###### member={}", member);
        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<MemberDto> list(Pageable pageable)
    {
        Page<Member> page = memberRepository.findAll(pageable);
        return page.map(m-> new MemberDto(m.getId(),m.getUsername(),m.getAge(), null));
    }



    @PostConstruct
    public void init()
    {
        for (int i=1; i <= 100; i++)
        {
            Member member = new Member("member" + i, 10 + (i%10));
            memberRepository.save(member);
        }
    }
}
