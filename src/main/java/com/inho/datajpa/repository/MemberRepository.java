package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>
{

    // [01] method 이름으로 쿼리 생성
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    // [02] @Query 어노테이션 없는 경우에는, 관례에 따라 찾는다 ( EntityName + methodName )
    @Query(name="Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    // [03] @Query 어노테이션을 사용해서 Repository Interface 에 쿼리 직접 정의
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findByUsernameAndAge(String username, int age);

    // [04] Collection 타입으로 in 쿼리
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);
}
