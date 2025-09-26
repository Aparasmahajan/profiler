package com.apex_aura.profiler.service.serviceImpl;

import com.apex_aura.profiler.builder.ResponseBuilderFactory;
import com.apex_aura.profiler.constants.ResponseConstant;
import com.apex_aura.profiler.dto.ResponseDTO;
import com.apex_aura.profiler.entity.Role;
import com.apex_aura.profiler.repository.RoleRepository;
import com.apex_aura.profiler.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseDTO getAllRoles() {
        Optional<List<Role>> roles;
        try {
            roles= Optional.of(roleRepository.findAll());
        }catch (Exception e) {
            return ResponseBuilderFactory.getResponse(ResponseConstant.FAILURE_MESSAGE_FETCH_REPOSITORY, ResponseConstant.FAILURE_CODE);
        }

        if(roles.isPresent()) {
            return ResponseBuilderFactory.getResponse(ResponseConstant.DETAILS_FETCH_SUCCESS, ResponseConstant.SUCCESS_CODE, roles.get());
        }
        return ResponseBuilderFactory.getResponse(ResponseConstant.DATA_NOT_FOUND, ResponseConstant.DETAILS_FETCH_SUCCESS, roles.get());
    }
}
