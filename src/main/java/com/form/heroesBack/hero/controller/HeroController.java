package com.form.heroesBack.hero.controller;

import javax.websocket.server.PathParam;

import com.form.heroesBack.hero.entity.Hero;
import com.form.heroesBack.hero.repository.HeroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
// import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

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
        log.info("Fin");
        return ResponseEntity.ok(entityModel);
    }
}
