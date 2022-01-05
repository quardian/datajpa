package com.inho.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@ToString(exclude = {"team"})

@Entity
@SequenceGenerator(name="MEMBER_ID_SEQ_GENERATOR", sequenceName = "MEMBER_ID_SEQ", initialValue = 1, allocationSize = 1)
@NamedQuery( name="Member.findByUsername", query="select m from Member m where m.username = :username")

@NamedEntityGraph(name="Member.all", attributeNodes = {@NamedAttributeNode("team")} )

public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_ID_SEQ_GENERATOR")
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    /** 편의 메소드 */
    public void changeTeam(Team team)
    {
        /* 현재 team 에서 현재 member 삭제 */
        if ( getTeam() != null ){
            getTeam().getMembers().remove(this);
        }

        /* 현재 객체 team 설정*/
        this.team = team;

        /* 새로운 team에 현재 member 추가 */
        if ( team != null ){
            team.getMembers().add(this);
        }
    }

    public void changeUserName(String name)
    {
        this.setUsername(name);
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        this.changeTeam(team);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return getAge() == member.getAge() && Objects.equals(getId(), member.getId()) && Objects.equals(getUsername(), member.getUsername()) && Objects.equals(getTeam(), member.getTeam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getAge(), getTeam());
    }
}
