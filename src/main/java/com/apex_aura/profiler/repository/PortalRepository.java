package com.apex_aura.profiler.repository;

import com.apex_aura.profiler.entity.Portal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortalRepository extends JpaRepository<Portal, Long> {
    Optional<Portal> findByPortalName(String portalName);
}
