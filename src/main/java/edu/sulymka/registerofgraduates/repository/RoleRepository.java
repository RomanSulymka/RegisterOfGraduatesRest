package edu.sulymka.registerofgraduates.repository;

import edu.sulymka.registerofgraduates.model.ERole;
import edu.sulymka.registerofgraduates.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
    Optional<Role> findRoleById(Long id);
}
