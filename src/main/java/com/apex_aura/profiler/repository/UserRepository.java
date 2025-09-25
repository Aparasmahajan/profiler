package com.apex_aura.profiler.repository;

import com.apex_aura.profiler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
