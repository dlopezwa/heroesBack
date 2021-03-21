package com.form.heroesBack.mission;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
public class Mission {
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(nullable=false)
    private String instructions;

}
