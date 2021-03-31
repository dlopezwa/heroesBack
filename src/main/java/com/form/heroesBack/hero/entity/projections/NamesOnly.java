package com.form.heroesBack.hero.entity.projections;
import com.form.heroesBack.hero.entity.Hero;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "namesOnly", types = { Hero.class })
public interface NamesOnly {
    String getFirstName();
    String getLastName();
}
