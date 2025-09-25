package com.apex_aura.profiler.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "content_access")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContentAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "granted_content_folder_id")
    private Folder grantedContentFolder;

    @ManyToOne
    @JoinColumn(name = "granted_by")
    private User grantedBy;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private ZonedDateTime grantedAt;

    private Boolean isActive = true;
}
