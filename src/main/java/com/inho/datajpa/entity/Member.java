package com.inho.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@ToString(exclude = {"team"})

@Entity
@SequenceGenerator(name="MEMBER_ID_SEQ_GENERATOR", sequenceName = "MEMBER_ID_SEQ", initialValue = 1, allocationSize = 1)
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

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        this.changeTeam(team);
    }
}
