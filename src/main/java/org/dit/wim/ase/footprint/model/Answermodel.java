package org.dit.wim.ase.footprint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answermodel {
    @Id @GeneratedValue
    private Integer Answer_id;
    private LocalDate Date;
    private LocalTime Time;
    @ManyToOne
//    @JoinColumn(name = "T_id")
    private Transportmodel transportmethod;
    private Integer  Distance;
    private Integer  Passenger_count;
    @ManyToOne
//    @JoinColumn(name = "User_id")
    private UserProperty user;
}
