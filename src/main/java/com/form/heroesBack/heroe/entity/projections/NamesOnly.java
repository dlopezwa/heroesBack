package com.form.heroesBack.heroe.entity.projections;
import com.form.heroesBack.heroe.entity.Heroe;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "namesOnly", types = { Heroe.class })
public interface NamesOnly {
    String getFirstName();
    String getLastName();
}
