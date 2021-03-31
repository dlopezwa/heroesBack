package com.form.heroesBack.mission.entity.projections;

import com.form.heroesBack.mission.entity.Mission;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "missionETO", types = { Mission.class })
public interface MissionETO {

    public Long getId();

    public String getInstructions();

}