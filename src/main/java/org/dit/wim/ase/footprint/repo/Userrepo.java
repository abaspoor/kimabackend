package org.dit.wim.ase.footprint.repo;

import org.dit.wim.ase.footprint.model.UserProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepo extends JpaRepository<UserProperty, Integer> {

//    UserProperty findByUsername(String username);
}
