package com.form.heroesBack.mission.repository;

import com.form.heroesBack.mission.entity.Mission;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = Mission.class)
public interface MissionRepository extends CrudRepository<Mission, Long> {
}