package com.apex_aura.profiler.controller;

import com.apex_aura.profiler.constants.UserConstant;
import com.apex_aura.profiler.dto.ResponseDTO;
import com.apex_aura.profiler.dto.requestDto.UserRequestDto;
import com.apex_aura.profiler.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(UserConstant.CREATE_USER)
    ResponseDTO createUser(@RequestBody UserRequestDto userRequest){
        return userService.createUser(userRequest);
    }

    @PostMapping(UserConstant.UPDATE_USER)
    ResponseDTO updateUser(@RequestBody UserRequestDto userRequest){
        return userService.updateUser(userRequest);
    }

    @PostMapping(UserConstant.USER_LOGIN)
    ResponseDTO userLogin(@RequestBody UserRequestDto userRequest){
        return userService.userLogin(userRequest);
    }
}
