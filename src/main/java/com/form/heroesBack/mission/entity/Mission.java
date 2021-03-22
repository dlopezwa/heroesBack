package com.form.heroesBack.mission.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Mission {
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(nullable=false)
    private String instructions;

}
