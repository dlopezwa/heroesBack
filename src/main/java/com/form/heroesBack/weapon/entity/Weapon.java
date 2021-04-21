package com.form.heroesBack.weapon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.form.heroesBack.hero.entity.Hero;


import lombok.Data;

@Data
@Entity
public class Weapon {
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(nullable=false)
    private String name;

    @ManyToOne()
    @JoinColumn(name="heroid", nullable=false)
    private Hero hero;
}
