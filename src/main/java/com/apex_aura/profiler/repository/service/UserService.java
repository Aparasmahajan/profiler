package com.apex_aura.profiler.repository.service;

import com.apex_aura.profiler.dto.ResponseDTO;
import com.apex_aura.profiler.dto.requestDto.UserRequestDto;

public interface UserService {
    ResponseDTO createUser(UserRequestDto userRequest);

    ResponseDTO updateUser(UserRequestDto userRequest);

    ResponseDTO userLogin(UserRequestDto userRequest);
}
