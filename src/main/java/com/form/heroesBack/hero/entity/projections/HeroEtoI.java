package com.form.heroesBack.hero.entity.projections;
import com.form.heroesBack.hero.entity.Hero;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "heroETO", types = { Hero.class })
public interface HeroEtoI {
    Long getId();
    String getFirstName();
    String getLastName();
    String getHeroName();
    String getHeroPower();
}
