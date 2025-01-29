package org.dit.wim.ase.footprint.repo;

import org.dit.wim.ase.footprint.entity.UserProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserProperty, Integer> {

}
