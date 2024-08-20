package com.vtn.service;

import com.vtn.dto.user.request.UserRequestDTO;
import com.vtn.dto.user.request.UserUpdateRequestDTO;
import com.vtn.dto.user.response.UserResponseDTO;
import com.vtn.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);
    void confirmUser(Long userId);
    void updateUser(UserUpdateRequestDTO userUpdateRequestDTO);
    UserResponseDTO getUserDetails(Long userId);
    void requestDeleteUser(Long userId);
    List<User> findUser(Map<String, String> params);
}
