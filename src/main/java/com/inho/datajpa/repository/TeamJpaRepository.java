package com.inho.datajpa.repository;

import com.inho.datajpa.entity.Member;
import com.inho.datajpa.entity.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class TeamJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Team save(Team team)
    {
        em.persist(team);
        return team;
    }

    public void delete(Team team)
    {
        em.remove(team);
    }

    public Team find(Long id){
        return em.find(Team.class, id);
    }

    public Optional<Team> findById(Long id)
    {
        Team team = em.find(Team.class, id);
        return Optional.ofNullable(team);
    }


    public List<Team> findAll()
    {
        String qlString = "select t from Team t";
        TypedQuery<Team> query = em.createQuery(qlString, Team.class);
        return query.getResultList();
    }

    public long count()
    {
        String qlString = "select count(t) from Team t";
        TypedQuery<Long> query = em.createQuery(qlString, Long.class);
        return query.getSingleResult();
    }

}
