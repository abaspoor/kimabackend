package org.dit.wim.ase.footprint.repo;

import org.dit.wim.ase.footprint.model.Answermodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface Answerrepo extends JpaRepository<Answermodel, Integer> {
}