package com.form.heroesBack.hero.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.form.heroesBack.mission.entity.Mission;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Entity
public class Hero {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private String heroName;

    @NotNull
    @Column(nullable = false)
    private String heroPower;

    @ManyToMany(mappedBy = "heroes")
    private List<Mission> missions;

}
