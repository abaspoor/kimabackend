package org.dit.wim.ase.footprint.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="UserProperty")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="User_Id")
    private Integer User_id;
    @Column(name="UserName")
    private String Username;
    @Column(name="PassWord")
    private String Password;
    @Column(name="EmaiL",unique = true)
    private String Email;
    @Column(name="FirstName")
    private String Firstname;
    @Column(name="LastName")
    private String Lastname;
    @OneToMany(mappedBy = "userproperty", fetch = FetchType.LAZY)
    private List<Answermodel> Answers;
}
