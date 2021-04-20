package com.form.heroesBack.hero.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import com.form.heroesBack.hero.entity.Hero;

@CrossOrigin
@RepositoryRestResource(excerptProjection = Hero.class)
public interface HeroRepository extends CrudRepository<Hero, Long> {

    public List<Hero> findByFirstNameStartingWithIgnoreCase(@Param("firstName") String firstName);

    @Query(value = "SELECT * FROM HERO WHERE first_name = :firstName", nativeQuery = true)
    public List<Hero> findByName(@Param("firstName") String firstName);

}
