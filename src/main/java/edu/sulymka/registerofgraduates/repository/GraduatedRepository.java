package edu.sulymka.registerofgraduates.repository;

import edu.sulymka.registerofgraduates.model.Graduated;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GraduatedRepository extends JpaRepository<Graduated, Long> {
    void deleteGraduatedById(Long id);

    Optional<Graduated> findGraduatedById(Long id);
}
