package edu.sulymka.registerofgraduates.repository;

import edu.sulymka.registerofgraduates.model.Graduated;
import edu.sulymka.registerofgraduates.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users where email =?1", nativeQuery = true)
    User getUserByEmail(String email);

    Optional<User> findUserById(Long id);
}
