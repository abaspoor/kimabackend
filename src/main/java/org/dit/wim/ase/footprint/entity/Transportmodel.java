package org.dit.wim.ase.footprint.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="Transportmodel")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transportmodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="T_Id")
    private Integer T_id;
    @Column(name="TransportName")
    private String Transportname;
    @Column(name="Fuel_Factor")
    private Integer Fuel_factor;
    @Column(name="Emission_Factor")
    private Integer Emission_factor;
    @OneToMany(mappedBy = "transportmodel", fetch = FetchType.LAZY)
    private List<Answermodel> Answers;
}
