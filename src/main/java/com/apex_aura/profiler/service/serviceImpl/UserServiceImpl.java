package com.apex_aura.profiler.service.serviceImpl;

import com.apex_aura.profiler.builder.ResponseBuilderFactory;
import com.apex_aura.profiler.constants.MessageConstant;
import com.apex_aura.profiler.constants.ResponseConstant;
import com.apex_aura.profiler.dto.ResponseDTO;
import com.apex_aura.profiler.dto.requestDto.UserRequestDto;
import com.apex_aura.profiler.dto.responseDto.UserLimitedResponseDto;
import com.apex_aura.profiler.entity.Role;
import com.apex_aura.profiler.entity.User;
import com.apex_aura.profiler.repository.PortalRepository;
import com.apex_aura.profiler.repository.RoleRepository;
import com.apex_aura.profiler.repository.UserRepository;
import com.apex_aura.profiler.service.UserService;
import com.apex_aura.profiler.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PortalRepository portalRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseDTO createUser(UserRequestDto userRequest) {
        Optional<User> user=userRepository.findByUsername(userRequest.getUsername());
        if(user.isPresent()){
            LinkedHashMap<String, Object> response = new LinkedHashMap<>();
            response.put("status", "fail");
            response.put("message", "Username already exists");
            return ResponseBuilderFactory.getResponse(MessageConstant.USERNAME_ALREADY_TAKEN, ResponseConstant.ALREADY_TAKEN);
        }

        user=userRepository.findByEmail(userRequest.getEmail());
        if(user.isPresent()){
            LinkedHashMap<String, Object> response = new LinkedHashMap<>();
            response.put("status", "fail");
            response.put("message", "Email already exists");
            return ResponseBuilderFactory.getResponse(MessageConstant.USERNAME_ALREADY_TAKEN, ResponseConstant.ALREADY_TAKEN);
        }

        User newUser=new User();
        try{
            BeanUtils.copyProperties(userRequest, newUser);

            if (userRequest.getRoleIds() != null && !userRequest.getRoleIds().isEmpty()) {
                Set<Role> roles = new HashSet<>(roleRepository.findAllById(userRequest.getRoleIds()));
                newUser.setRoles(roles);
            }

            userRepository.save(newUser);

            return ResponseBuilderFactory.getResponse(ResponseConstant.DETAILS_FETCH_SUCCESS, ResponseConstant.SUCCESS_CODE, newUser);

        }catch (Exception e){
            return ResponseBuilderFactory.getResponse(ResponseConstant.FAILURE_MESSAGE, ResponseConstant.FAILURE_CODE, e.getStackTrace());
        }
    }

    @Override
    public ResponseDTO updateUser(UserRequestDto userRequest) {
        Optional<User> user=userRepository.findByUsername(userRequest.getUsername());
        if(!user.isPresent()){
            return ResponseBuilderFactory.getResponse(MessageConstant.USERNAME_NOT_FOUND, ResponseConstant.DATA_NOT_FOUND);
        }

        try{
            BeanUtils.copyProperties(userRequest, user.get());

            if (userRequest.getRoleIds() != null && !userRequest.getRoleIds().isEmpty()) {
                Set<Role> roles = new HashSet<>(roleRepository.findAllById(userRequest.getRoleIds()));
                user.get().setRoles(roles);
            }

            userRepository.save(user.get());

            return ResponseBuilderFactory.getResponse(ResponseConstant.SUCCESS_MESSAGE, ResponseConstant.SUCCESS_CODE, user.get());

        }catch (Exception e){
            return ResponseBuilderFactory.getResponse(ResponseConstant.FAILURE_MESSAGE, ResponseConstant.FAILURE_CODE, e.getStackTrace());
        }
    }


    @Override
    public ResponseDTO userLogin(UserRequestDto userRequest) {
        Optional<User> userOpt = userRepository.findByUsername(userRequest.getUsername());
        if (userOpt.isEmpty()) {
            return ResponseBuilderFactory.getResponse(
                    MessageConstant.USERNAME_NOT_FOUND,
                    ResponseConstant.DATA_NOT_FOUND
            );
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(userRequest.getPassword())) {
            return ResponseBuilderFactory.getResponse(
                    ResponseConstant.INVALID_PASSWORD,
                    ResponseConstant.FAILURE_CODE
            );
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getUserId());

        LinkedHashMap<String, Object> payload = new LinkedHashMap<>();
        payload.put("token", token);
        payload.put("username", user.getUsername());
        payload.put("email", user.getEmail());
        payload.put("roles", user.getRoles());

        payload.put("portals",portalRepository.findPortalNamesByUserId(user.getUserId()));

        return ResponseBuilderFactory.getResponse(
                ResponseConstant.SUCCESS_MESSAGE,
                ResponseConstant.SUCCESS_CODE,
                payload
        );
    }


    @Override
    public ResponseDTO isUserAdmin(Long userId, Long portalId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseBuilderFactory.getResponse(
                    MessageConstant.USERNAME_NOT_FOUND,
                    ResponseConstant.DATA_NOT_FOUND
            );
        }

        User user = userOpt.get();

        boolean isAdmin = portalRepository.existsByPortalIdAndAdmins_UserId(portalId, userId);

        LinkedHashMap<String, Object> payload = new LinkedHashMap<>();
        payload.put("isAdmin", isAdmin);

        return ResponseBuilderFactory.getResponse(
                ResponseConstant.SUCCESS_MESSAGE,
                ResponseConstant.SUCCESS_CODE,
                payload
        );
    }



    @Override
    public ResponseDTO findByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseBuilderFactory.getResponse(
                    MessageConstant.USERNAME_NOT_FOUND,
                    ResponseConstant.DATA_NOT_FOUND
            );
        }

        UserLimitedResponseDto userLimitedResponseDto = new UserLimitedResponseDto();
        BeanUtils.copyProperties(userOpt.get(), userLimitedResponseDto);

        return ResponseBuilderFactory.getResponse(
                ResponseConstant.SUCCESS_MESSAGE,
                ResponseConstant.SUCCESS_CODE,
                userOpt.get()
        );
    }

}
