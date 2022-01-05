package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import com.inho.datajpa.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
