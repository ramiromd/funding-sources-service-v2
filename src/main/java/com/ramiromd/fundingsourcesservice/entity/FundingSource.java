package com.ramiromd.fundingsourcesservice.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="source_type", length=16,
        discriminatorType = DiscriminatorType.STRING)
abstract public class FundingSource {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Long id;

    @Column(length=100, nullable=false)
    protected String name;

    @Column(length=36, nullable = false)
    protected String userId;

    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @Column
    protected LocalDateTime deletedAt;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @Transient
    public String getType() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }
}
