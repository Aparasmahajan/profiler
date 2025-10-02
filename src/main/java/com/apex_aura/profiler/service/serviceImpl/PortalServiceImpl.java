package com.apex_aura.profiler.service.serviceImpl;

import com.apex_aura.profiler.builder.ResponseBuilderFactory;
import com.apex_aura.profiler.constants.ResponseConstant;
import com.apex_aura.profiler.dto.ResponseDTO;
import com.apex_aura.profiler.dto.requestDto.PortalRequestDto;
import com.apex_aura.profiler.dto.responseDto.PortalResponseDto;
import com.apex_aura.profiler.entity.Portal;
import com.apex_aura.profiler.entity.Role;
import com.apex_aura.profiler.entity.User;
import com.apex_aura.profiler.repository.PortalRepository;
import com.apex_aura.profiler.repository.RoleRepository;
import com.apex_aura.profiler.repository.UserRepository;
import com.apex_aura.profiler.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PortalServiceImpl implements PortalService {
    @Autowired
    PortalRepository portalRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseDTO getPortalInfo(String portalName) {
        if (portalName == null || portalName.trim().isEmpty()) {
            return ResponseBuilderFactory.getResponse(
                    ResponseConstant.EMPTY_FIELD,
                    ResponseConstant.DATA_NOT_FOUND
            );
        }

        Optional<Portal> portalOptional = portalRepository.findByPortalName(portalName);

        if (portalOptional.isEmpty()) {
            return ResponseBuilderFactory.getResponse(
                    ResponseConstant.DATA_NOT_FOUND,
                    ResponseConstant.DATA_NOT_FOUND
            );
        }

        Portal portal = portalOptional.get();
        PortalResponseDto dto = PortalResponseDto.builder()
                .portalId(portal.getPortalId())
                .portalName(portal.getPortalName())
                .isActive(portal.getIsActive())
                .createdAt(portal.getCreatedAt())
                .updatedAt(portal.getUpdatedAt())
                .admins(
                        portal.getAdmins() != null
                                ? portal.getAdmins().stream()
                                .map(user -> PortalResponseDto.AdminSummaryDTO.builder()
                                        .username(user.getUsername())
                                        .email(user.getEmail())
                                        .fullName(user.getFullName())
                                        .userId(user.getUserId())
                                        .build())
                                .toList()
                                : List.of()
                )

                .build();

        return ResponseBuilderFactory.getResponse(
                ResponseConstant.SUCCESS_MESSAGE,
                ResponseConstant.SUCCESS_CODE,
                dto
        );
    }

    @Override
    public ResponseDTO createPortalInfo(PortalRequestDto portalRequestDto) {
        if (portalRequestDto.getPortalName() == null || portalRequestDto.getPortalName().trim().isEmpty() ||
                portalRequestDto.getAdminId() == null ||
                portalRequestDto.getIsActive() == null || portalRequestDto.getIsActive().trim().isEmpty() ||
                portalRequestDto.getCreaterId() == null) {
            return ResponseBuilderFactory.getResponse(
                    ResponseConstant.EMPTY_FIELD,
                    ResponseConstant.DATA_NOT_FOUND
            );
        }

        Optional<Portal> portalOptional = portalRepository.findByPortalName(portalRequestDto.getPortalName());
        if (portalOptional.isPresent()) {
            return ResponseBuilderFactory.getResponse(
                    ResponseConstant.PORTAL_ALREADY_EXISTS,
                    ResponseConstant.DATA_NOT_FOUND
            );
        }

        User creator = userRepository.findById(portalRequestDto.getCreaterId()).orElse(null);
        if (creator == null) {
            return ResponseBuilderFactory.getResponse("User not found", ResponseConstant.DATA_NOT_FOUND);
        }

        boolean isSuperUser = creator.getRoles().stream()
                .anyMatch(role -> "SUPER".equalsIgnoreCase(role.getName()));

        if (!isSuperUser) {
            return ResponseBuilderFactory.getResponse("No access for current user", ResponseConstant.UNAUTHORIZED_ACCESS);
        }

        // Fetch admin user from adminId
        User adminUser = userRepository.findById(portalRequestDto.getAdminId()).orElse(null);

        // Fetch all super admins
        Set<User> superAdmins = userRepository.findAll().stream()
                .filter(u -> u.getRoles().stream().anyMatch(r -> "SUPER".equalsIgnoreCase(r.getName())))
                .collect(Collectors.toSet());

        // Add adminUser to superAdmins if not already present
        if (adminUser != null) {
            superAdmins.add(adminUser);
        }

        Portal portal = new Portal();
        portal.setPortalName(portalRequestDto.getPortalName());
        portal.setIsActive(Boolean.parseBoolean(portalRequestDto.getIsActive()));
        portal.setAdmins(superAdmins); // assign all super admins + adminUser

        portalRepository.save(portal);

        return ResponseBuilderFactory.getResponse(
                ResponseConstant.SUCCESS_MESSAGE,
                ResponseConstant.SUCCESS_CODE,
                portal
        );
    }



}
