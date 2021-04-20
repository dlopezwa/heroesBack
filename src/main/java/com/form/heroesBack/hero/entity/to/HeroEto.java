package com.form.heroesBack.hero.entity.to;


import java.io.Serializable;

import lombok.Data;

@Data
public class HeroEto implements Serializable{

    private Long id;


    private String firstName;


    private String lastName;


    private String heroName;


    private String heroPower;

}
