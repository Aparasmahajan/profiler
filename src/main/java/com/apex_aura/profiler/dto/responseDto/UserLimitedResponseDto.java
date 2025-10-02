package com.apex_aura.profiler.dto.responseDto;

import lombok.Data;

@Data
public class UserLimitedResponseDto {
    private String username;
    private String email;
    private String fullName;
    private Long userId;
}
