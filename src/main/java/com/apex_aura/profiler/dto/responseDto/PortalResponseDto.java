package com.apex_aura.profiler.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortalResponseDto {

    private Long portalId;
    private String portalName;
    private Boolean isActive;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    // A lightweight view of admin users
    private List<AdminSummaryDTO> admins;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AdminSummaryDTO {
        private Long userId;
        private String username;
        private String fullName;
        private Boolean isActive;
        private String email;
    }
}