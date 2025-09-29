package com.apex_aura.profiler.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "portal")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Portal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portalId;

    @Column(name = "portal_name", length = 100)
    private String portalName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "portal_admins",
            joinColumns = @JoinColumn(name = "portal_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> admins;


    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Column
    private ZonedDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        ZoneId indiaZone = ZoneId.of("Asia/Kolkata");
        this.createdAt = ZonedDateTime.now(indiaZone);
    }

    @PreUpdate
    protected void onUpdate() {
        ZoneId indiaZone = ZoneId.of("Asia/Kolkata"); // GMT+5:30
        this.updatedAt = ZonedDateTime.now(indiaZone);
    }

    private Boolean isActive = true;
}
