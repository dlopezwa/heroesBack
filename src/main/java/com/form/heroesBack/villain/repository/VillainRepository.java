package com.form.heroesBack.villain.repository;

import java.util.List;

import com.form.heroesBack.villain.entity.Villain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Repository
public interface VillainRepository extends PagingAndSortingRepository<Villain, Long>{
    
    public List<Villain> findByVillainNameContainingIgnoreCase(@Param("VillainName") String VillainName);
}
