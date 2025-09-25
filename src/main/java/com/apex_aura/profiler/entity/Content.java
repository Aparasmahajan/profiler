package com.apex_aura.profiler.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "contents")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "content_type", length = 20, nullable = false)
    private String contentType;

    @Column(name = "storage_path", columnDefinition = "TEXT", nullable = false)
    private String storagePath;

    private Double price;

    @Column(name = "is_public")
    private Boolean isPublic = false;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private ZonedDateTime createdAt;

    private Boolean isActive = true;
}
