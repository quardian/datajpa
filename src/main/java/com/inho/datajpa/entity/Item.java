package com.inho.datajpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Item implements Persistable<String>
{
    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdAt;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return createdAt == null;
    }

    public Item() {
    }

    public Item(String id) {
        this.id = id;
    }
}
