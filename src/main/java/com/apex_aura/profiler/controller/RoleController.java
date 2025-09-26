package com.apex_aura.profiler.controller;

import com.apex_aura.profiler.constants.PortalConstant;
import com.apex_aura.profiler.constants.RoleConstant;
import com.apex_aura.profiler.dto.ResponseDTO;
import com.apex_aura.profiler.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RoleConstant.ROLE)
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping(RoleConstant.GET_ALL_ROLES)
    ResponseDTO userLogin(@RequestBody String portalName) {
        return roleService.getAllRoles();
    }
}
