package com.form.heroesBack.hero.controller;

import javax.websocket.server.PathParam;

import com.form.heroesBack.hero.entity.Hero;
import com.form.heroesBack.hero.repository.HeroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class HeroController {

    @Autowired
    private HeroRepository heroRepository;

    @GetMapping(value = "/custom/search/{id}")
    public Hero getHeroUpperCase(@PathParam("id") long id) {

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
        return hero;
    }

}
