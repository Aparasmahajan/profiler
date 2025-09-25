package com.apex_aura.profiler.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "portal")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Portal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "portal_name", length = 100)
    private String portalName;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private ZonedDateTime createdAt;

    private Boolean isActive = true;
}
