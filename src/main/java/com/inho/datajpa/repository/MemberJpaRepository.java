package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Member save(Member member)
    {
        em.persist(member);
        return member;
    }

    public void delete(Member member)
    {
        em.remove(member);
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }

    public Optional<Member> findById(Long id)
    {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findMyUsername(String username)
    {
        String qlString = "select m from Member m where m.username = :username";
        TypedQuery<Member> query = em.createQuery(qlString, Member.class);
        return query
                .setParameter("username", username)
                .getResultList();
    }



    public List<Member> findAll()
    {
        String qlString = "select m from Member m";
        TypedQuery<Member> query = em.createQuery(qlString, Member.class);
        return query.getResultList();
    }

    public long count()
    {
        String qlString = "select count(m) from Member m";
        TypedQuery<Long> query = em.createQuery(qlString, Long.class);
        return query.getSingleResult();
    }

}
