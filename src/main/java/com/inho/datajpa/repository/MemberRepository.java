package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    // [05] Paging ( count 쿼리 포함 )
    Page<Member> findPageByAge(int age, Pageable pageable);

    // [06] Pageing-Slice
    Slice<Member> findSliceByAge(int age, Pageable pageable);

    // [07] Pageing-List
    List<Member> findListByAge(int age, Pageable pageable);

    // [08] 사용자가 정의한 쿼리 ( Sort, Paging ==> Spring Data JPA가 처리함 )
    @Query(  value="select m from Member m left join m.team t where m.age = :age"
            ,countQuery = "select count(m) from Member m where m.age = :age"
    )
    Page<Member> findCustomPageByAge(@Param("age") int age, Pageable pageable);

    // [09] bulk update : @Modifying 애노테이션이 있어야 JPA의 executeUpdate() 메소드 호출함.
    @Modifying(clearAutomatically = true)
    @Query( value = "update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    // [10] fetch join with JPA
    @Query( "select m from Member m inner join fetch m.team ")
    List<Member> findFetchAll();

    // [11] fetch join with EntityGraph supported Spring DATA Jpa
    //@EntityGraph(attributePaths = {"team"} )
    @EntityGraph( value="Member.all" )
    List<Member> findFetchById(Long id);
}
