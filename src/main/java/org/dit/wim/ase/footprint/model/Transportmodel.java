package org.dit.wim.ase.footprint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transportmodel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer T_id;
    private String transportname;
    private Integer Fuel_factor;
    private Integer Emission_factor;
    @OneToMany
    private Set<Answermodel> Answers;
}
