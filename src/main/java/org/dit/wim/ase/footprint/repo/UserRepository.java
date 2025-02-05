package org.dit.wim.ase.footprint.repo;

import org.dit.wim.ase.footprint.entity.UserProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserProperty, Integer> {
    @Query("SELECT u FROM UserProperty u WHERE u.Email = :email")
    Optional<UserProperty> findByEmail(String email); // ✅ For checking email uniqueness
}
