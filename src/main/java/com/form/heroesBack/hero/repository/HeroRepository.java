package com.form.heroesBack.hero.repository;

import java.util.List;

import com.form.heroesBack.hero.entity.Hero;
import com.form.heroesBack.hero.entity.interfaces.HeroWeaponDtoI;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin 
@RepositoryRestResource(excerptProjection = Hero.class)
public interface HeroRepository extends CrudRepository<Hero, Long> {

    public List<Hero> findByFirstNameStartingWithIgnoreCase(@Param("firstName") String firstName);


    @Query(value="SELECT * FROM HERO WHERE first_name = :firstName", nativeQuery = true)
    public List<Hero> findByName(@Param("firstName") String firstName);


    // Para que no se exporte como servicio rest disponible.
    @RestResource(exported = false)
    @Query(value="SELECT H.HERO_NAME AS HERO, W.NAME AS WEAPON FROM HERO H INNER JOIN WEAPON W ON W.HEROID=H.ID", nativeQuery = true)
    public List<HeroWeaponDtoI> findHeroWeapon();

}
