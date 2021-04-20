package com.form.heroesBack.mission.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.form.heroesBack.hero.entity.Hero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Mission {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(nullable = false)
    private String instructions;

    @ManyToMany()
    @JoinTable(name = "hero_mission", joinColumns = @JoinColumn(name = "missionid", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "heroid", referencedColumnName = "id", nullable = false))
    private List<Hero> heroes;
}
