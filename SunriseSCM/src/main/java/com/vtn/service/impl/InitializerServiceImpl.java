package com.vtn.service.impl;

import com.vtn.dto.user.request.UserRequestDTO;
import com.vtn.enums.RoleEnum;
import com.vtn.pojo.Account;
import com.vtn.pojo.User;
import com.vtn.service.InitializerService;
import com.vtn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class InitializerServiceImpl implements InitializerService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createAdminUser() {
        Map<String, String> params = new HashMap<>();
        params.put("role", "ROLE_ADMIN");
        List<User> users = this.userService.findUser(params);
        if (users.isEmpty()) {

            UserRequestDTO admin = UserRequestDTO.builder()
                    .fullName("Quản trị viên")
                    .email("admin@vtn.com")
                    .role(RoleEnum.ROLE_ADMIN)
                    .username("admin")
                    .password("123456")
                    .build();

            this.userService.registerUser(admin);
        }
    }
}
