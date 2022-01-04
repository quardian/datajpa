package com.inho.datajpa.web.controoler;

import com.inho.datajpa.entity.Member;
import com.inho.datajpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping
    public ResponseEntity<Member> hello(){
        Member member = new Member("Name1");
        Member savedMember = memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findById(1L);

        return new ResponseEntity<Member>(findMember.get(), HttpStatus.OK);
    }
}
