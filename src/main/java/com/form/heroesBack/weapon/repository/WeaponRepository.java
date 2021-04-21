package com.form.heroesBack.weapon.repository;

import com.form.heroesBack.weapon.entity.Weapon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(excerptProjection = Weapon.class)
public interface WeaponRepository extends CrudRepository<Weapon, Long> {

}
