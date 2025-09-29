package com.apex_aura.profiler.service;

import com.apex_aura.profiler.dto.ResponseDTO;
import com.apex_aura.profiler.dto.requestDto.PortalRequestDto;
import com.apex_aura.profiler.dto.requestDto.UserRequestDto;

public interface PortalService {
    ResponseDTO getPortalInfo(String portalName);

    ResponseDTO createPortalInfo(PortalRequestDto portalRequestDto);
}
