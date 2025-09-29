package com.apex_aura.profiler.repository;

import com.apex_aura.profiler.entity.Portal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortalRepository extends JpaRepository<Portal, Long> {
    Optional<Portal> findByPortalName(String portalName);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Portal p JOIN p.admins a " +
            "WHERE p.portalId = :portalId AND a.userId = :userId")
    boolean existsByPortalIdAndAdminUserId(@Param("portalId") Long portalId,
                                           @Param("userId") Long userId);

    boolean existsByPortalIdAndAdmins_UserId(Long portalId, Long userId);

}
