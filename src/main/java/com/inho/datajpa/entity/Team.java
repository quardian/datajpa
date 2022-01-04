package com.inho.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@ToString(exclude = {"members"})

@Entity
@SequenceGenerator(name= "team_id_seq_generator", sequenceName = "team_id_seq", initialValue = 1, allocationSize = 1)
public class Team {
    @Id @GeneratedValue(generator = "team_id_seq_generator")
    @Column(name = "team_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
