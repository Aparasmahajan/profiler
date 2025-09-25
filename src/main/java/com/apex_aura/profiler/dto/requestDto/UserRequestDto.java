package com.apex_aura.profiler.dto.requestDto;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String username;
    private String email;
    private String password;
    private String fullName;
    private Long portalId;
    private Set<Long> roleIds;
    private Boolean canComment;
    private Boolean isActive;
}
