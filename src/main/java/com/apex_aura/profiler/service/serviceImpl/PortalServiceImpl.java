package com.apex_aura.profiler.service.serviceImpl;

import com.apex_aura.profiler.builder.ResponseBuilderFactory;
import com.apex_aura.profiler.constants.ResponseConstant;
import com.apex_aura.profiler.dto.ResponseDTO;
import com.apex_aura.profiler.entity.Portal;
import com.apex_aura.profiler.repository.PortalRepository;
import com.apex_aura.profiler.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PortalServiceImpl implements PortalService {
    @Autowired
    PortalRepository portalRepository;

    @Override
    public ResponseDTO getPortalInfo(String portalName) {
        if (portalName == null || portalName.trim().isEmpty()) {
            return ResponseBuilderFactory.getResponse(
                    ResponseConstant.EMPTY_FIELD,
                    ResponseConstant.DATA_NOT_FOUND
            );
        }

        Optional<Portal> portalOptional = portalRepository.findByPortalName(portalName);

        Portal portal = new Portal();
        if(portalOptional.isEmpty()) {
            return ResponseBuilderFactory.getResponse(
                    ResponseConstant.DATA_NOT_FOUND,
                    ResponseConstant.DATA_NOT_FOUND
            );
        } else {
            portal = portalOptional.orElse(null);
        }


        return ResponseBuilderFactory.getResponse(
                ResponseConstant.SUCCESS_MESSAGE,
                ResponseConstant.SUCCESS_CODE,
                portal
        );
    }

}
