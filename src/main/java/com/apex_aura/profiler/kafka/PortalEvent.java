package com.apex_aura.profiler.kafka;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortalEvent {
    private String eventType; // CREATED, UPDATED, DEACTIVATED, ADMIN_ADDED, ADMIN_REMOVED
    private Long portalId;
    private String portalName;
    private Long adminId; // optional, for admin events
    private Boolean isActive;
    private LocalDateTime timestamp;
}
