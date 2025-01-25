package org.dit.wim.ase.footprint.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class UserProperty {
    @Id @GeneratedValue
    private Integer User_id;
    private String Username;
    private String Password;
    private String email;
    private String Firstname;
    private String Lastname;
    @OneToMany
    private Set<Answermodel> Answers;
//    @ElementCollection(fetch = FetchType.EAGER) private List<String> roles;
//
//
//    public UserDetails asUserDetails(PasswordEncoder passwordEncoder) {
//        return User.builder()
//                .username(Username)
//                .password(passwordEncoder.encode(Password))
//                .roles(roles.toArray(new String[0]))
//                .build();
//    }
}
