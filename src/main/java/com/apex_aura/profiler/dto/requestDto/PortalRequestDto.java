package com.apex_aura.profiler.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortalRequestDto {
    private String portalName;
    private Long adminId;
    private String isActive;
    private Long createrId;
}
