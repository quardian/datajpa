package com.inho.datajpa.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private int age;
    private String teamName;
}
