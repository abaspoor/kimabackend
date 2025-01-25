package org.dit.wim.ase.footprint.repo;

import org.dit.wim.ase.footprint.model.Transportmodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface methodrepo extends JpaRepository<Transportmodel, Integer> {

}
