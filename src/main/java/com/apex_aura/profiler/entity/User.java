package com.apex_aura.profiler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    private String fullName;

    @ManyToMany(mappedBy = "admins", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Portal> portals;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Column(name = "can_comment", nullable = false)
    private Boolean canComment = true;

    private Boolean isActive = true;


    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Column
    private ZonedDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        ZoneId indiaZone = ZoneId.of("Asia/Kolkata"); // GMT+5:30
        this.createdAt = ZonedDateTime.now(indiaZone);
        this.updatedAt = ZonedDateTime.now(indiaZone);
    }

    @PreUpdate
    protected void onUpdate() {
        ZoneId indiaZone = ZoneId.of("Asia/Kolkata"); // GMT+5:30
        this.updatedAt = ZonedDateTime.now(indiaZone);
    }
}
