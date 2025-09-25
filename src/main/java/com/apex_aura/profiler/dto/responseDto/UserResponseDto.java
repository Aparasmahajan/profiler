package com.apex_aura.profiler.dto.responseDto;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private String username;
    private String email;
    private String fullName;
    private Long portalId;
    private String portalName;
    private Set<String> roles;
    private Boolean canComment;
    private Boolean isActive;
    private ZonedDateTime createdAt;
}

