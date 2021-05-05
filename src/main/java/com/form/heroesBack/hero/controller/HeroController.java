package com.form.heroesBack.hero.controller;

import com.form.heroesBack.hero.entity.Hero;
import com.form.heroesBack.hero.entity.interfaces.HeroWeaponDtoI;
import com.form.heroesBack.hero.entity.to.HeroWeaponDto;
import com.form.heroesBack.hero.repository.HeroRepository;
import com.form.heroesBack.mission.controller.MissionController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryRestController
@RequestMapping("/heroes")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class HeroController {

    @Autowired
    private HeroRepository heroRepository;

    @GetMapping(value = "/custom/search/{id}")
    public @ResponseBody ResponseEntity<EntityModel<Hero>> getHeroUpperCase(@PathVariable("id") long id) {
        log.info("Inicio");
        Hero hero = heroRepository.findById(id).orElse(null);

        // Retornamos sus campos en MAYUS.
        // El objetivo de este ejercicio es simular un servicio en el que debemos
        // procesar su salida o incluso operar con otros repositorios.
        if (hero != null) {
            hero.setFirstName(hero.getFirstName().toUpperCase());
            hero.setLastName(hero.getLastName().toUpperCase());
            hero.setHeroPower(hero.getHeroPower().toUpperCase());
            hero.setHeroName(hero.getHeroName().toUpperCase());
        }

        // Convertimos el objeto a HATEOAS.
        EntityModel<Hero> entityModel = EntityModel.of(hero);
        entityModel.add(linkTo(methodOn(HeroController.class).getHeroUpperCase(id)).withSelfRel());
        entityModel.add(linkTo(methodOn(MissionController.class).getMissionUpperCase(id)).withSelfRel());
        log.info("Fin");
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping(value = "/custom/search/heroweapon")
    public @ResponseBody ResponseEntity<CollectionModel<HeroWeaponDto>> findHeroWeapon() {
        List<HeroWeaponDtoI> listHeroWeaponI = heroRepository.findHeroWeapon();
        
        // A futuro modificar con Orika.
        List<HeroWeaponDto> listHeroWeapon = new ArrayList<>();
        for (HeroWeaponDtoI heroWeaponDtoI : listHeroWeaponI) {
            HeroWeaponDto heroWeaponDto = new HeroWeaponDto();
            heroWeaponDto.setHero(heroWeaponDtoI.getHero());
            heroWeaponDto.setWeapon(heroWeaponDtoI.getWeapon());

            listHeroWeapon.add(heroWeaponDto);
        }

        return ResponseEntity.ok(CollectionModel.of(listHeroWeapon));
    }
}

