package com.inho.datajpa.entity.mappedsuperclass;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class JpaBaseEntity {
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * JPA가 PrePersist 이벤트 제공 : Persist 하기 전 이벤트 발생
     */
    @PrePersist
    public void prePersist()
    {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    /**
     * JPA가 업데이트 하기 전 이벤트 제공
     */
    @PreUpdate
    public void preUpdate()
    {
        LocalDateTime now = LocalDateTime.now();
        updatedAt = now;
    }
}
