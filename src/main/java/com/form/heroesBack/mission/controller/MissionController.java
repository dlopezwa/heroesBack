package com.form.heroesBack.mission.controller;

import com.form.heroesBack.hero.controller.HeroController;
import com.form.heroesBack.mission.entity.Mission;
import com.form.heroesBack.mission.repository.MissionRepository;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryRestController
@RequestMapping("/missions")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class MissionController {

    @Autowired
    private MissionRepository missionRepository;

    @GetMapping(value = "/custom/search/{id}")
    public @ResponseBody ResponseEntity<EntityModel<Mission>> getMissionUpperCase(@PathVariable("id") long id) {
        log.info("Inicio");
        Mission mission = missionRepository.findById(id).orElse(null);

        // Retornamos sus campos en MAYUS.
        // El objetivo de este ejercicio es simular un servicio en el que debemos
        // procesar su salida o incluso operar con otros repositorios.
        if (mission != null) {
            mission.setInstructions(mission.getInstructions().toUpperCase());
        }

        // Convertimos el objeto a HATEOAS.
        EntityModel<Mission> entityModel = EntityModel.of(mission);
        entityModel.add(linkTo(methodOn(MissionController.class).getMissionUpperCase(id)).withSelfRel());
        entityModel.add(linkTo(methodOn(HeroController.class).getHeroUpperCase(id)).withSelfRel());
        log.info("Fin");
        return ResponseEntity.ok(entityModel);
    }

}
