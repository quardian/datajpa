package com.inho.datajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@SequenceGenerator(name="MEMBER_ID_SEQ_GENERATOR", sequenceName = "MEMBER_ID_SEQ", initialValue = 1, allocationSize = 1)
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_ID_SEQ_GENERATOR")
    private Long id;

    private String name;

    public Member(String name) {
        this.name = name;
    }
}
