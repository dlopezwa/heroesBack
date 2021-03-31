package com.form.heroesBack.mission.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import com.form.heroesBack.heroe.entity.Heroe;

import lombok.Data;


@Data
@Entity
public class Mission {
    
    @Id
    @GeneratedValue
    private long Id;
    
    @Column(nullable=false)
    private String instructions;

    @ManyToMany()
    @JoinTable(
        name = "heroe_mission",
        joinColumns = @JoinColumn(name = "missionid", referencedColumnName = "id", nullable = false),
        inverseJoinColumns = @JoinColumn(name="heroeid", referencedColumnName = "id", nullable = false)
    )
    private List<Heroe> heroes;
}
