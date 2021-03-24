package com.form.heroesBack.heroe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.form.heroesBack.mission.entity.Mission;

import lombok.Data;
@Data
@Entity
public class Heroe {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String heroName;

    @Column(nullable = false)
    private String heroPower;

    @ManyToMany(mappedBy = "heroes")
    private List<Mission> missions;


}
