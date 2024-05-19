package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    Boolean existsByEmail(String email);
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.nameRole = 'DONANTE'")
    List<User> findByRoleDonante();
    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.nameRole = 'DONANTE' and u.id = :id")
    Optional<User> findDonorById(@Param("id") Long id);

}
