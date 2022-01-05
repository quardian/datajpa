package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collection;
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

    public void saveAll( Collection<Member> members)
    {
        for (Member member : members) {
            em.persist(member);
        }
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

    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age)
    {
        String qlString = "select m from Member m where m.username = :username and m.age > :age";
        TypedQuery<Member> query = em.createQuery(qlString, Member.class);
        return query
                .setParameter("username", username)
                .setParameter("age", age)
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

    // NamedQuery 이용
    public List<Member> findByUsername(String username)
    {
        TypedQuery<Member> query = em.createNamedQuery("Member.findByUsername", Member.class);
        return query
                .setParameter("username", username)
                .getResultList();
    }

    public List<Member> findByPage(int age, int offset, int limit){
        String qlString = "select m from Member m where m.age = :age " +
                          " order by m.username desc ";
        return em.createQuery(qlString, Member.class)
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public long totalCount(int age){
        String qlString = "select count(m) from Member m where m.age = :age ";
        return em.createQuery(qlString, Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }

    // JPA를 사용한 벌크성 수정 쿼리
    public int bulkAgePlus(int age)
    {
        String qlString = "update Member m set m.age = m.age + 1 where m.age >= :age";

        int affected = em.createQuery(qlString)
                .setParameter("age", age)
                .executeUpdate();

        return affected;
    }
}
