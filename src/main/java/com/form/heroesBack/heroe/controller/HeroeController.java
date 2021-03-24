package com.form.heroesBack.heroe.controller;

import javax.websocket.server.PathParam;

import com.form.heroesBack.heroe.entity.Heroe;
import com.form.heroesBack.heroe.repository.HeroeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RepositoryRestController
@RequestMapping("/heroes")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class HeroeController {

    @Autowired
    private HeroeRepository heroeRepository;


    @GetMapping(value = "/custom/search/{id}")
    public Heroe getHeroeUpperCase(@PathParam("id") long id) {
        
        Heroe heroe = heroeRepository.findById(id).orElse(null);

        // Retornamos sus campos en MAYUS.
        // El objetivo de este ejercicio es simular un servicio en el que debemos procesar su salida o incluso operar con otros repositorios.
        if(heroe != null) {
            heroe.setFirstName(heroe.getFirstName().toUpperCase());
            heroe.setLastName(heroe.getLastName().toUpperCase());
            heroe.setHeroPower(heroe.getHeroPower().toUpperCase());
            heroe.setHeroName(heroe.getHeroName().toUpperCase());
        }
        return heroe;
    }
}
