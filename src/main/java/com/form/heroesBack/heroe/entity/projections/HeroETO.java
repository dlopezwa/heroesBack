package com.form.heroesBack.heroe.entity.projections;
import com.form.heroesBack.heroe.entity.Heroe;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "heroeETO", types = { Heroe.class })
public interface HeroETO {
    Long getId();
    String getFirstName();
    String getLastName();
    String getHeroName();
    String getHeroPower();
}
