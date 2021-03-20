package com.form.heroesBack.heroe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = Heroe.class)
public interface HeroeRepository extends CrudRepository<Heroe, Long>{
    
}
