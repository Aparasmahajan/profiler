package com.apex_aura.profiler.controller;

import com.apex_aura.profiler.constants.PortalConstant;
import com.apex_aura.profiler.dto.ResponseDTO;
import com.apex_aura.profiler.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PortalConstant.PORTAL)
public class PortalController {
    @Autowired
    PortalService portalService;

    @GetMapping(PortalConstant.GET_PORTAL_INFO)
    ResponseDTO userLogin(@RequestParam String portalName) {
        return portalService.getPortalInfo(portalName);
    }
}
