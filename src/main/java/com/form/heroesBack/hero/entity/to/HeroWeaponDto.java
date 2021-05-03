package com.form.heroesBack.hero.entity.to;

import com.form.heroesBack.hero.entity.interfaces.HeroWeaponDtoI;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HeroWeaponDto implements HeroWeaponDtoI{

    private String hero;
    private String weapon;
}