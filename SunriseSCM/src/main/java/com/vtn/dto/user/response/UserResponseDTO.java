package com.vtn.dto.user.response;

import com.vtn.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;

    @NotNull(message = "{user.fullName.notnullMsg}")
    private String fullName;

    private String address;

    private String phone;

    private String avatar;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "{user.email.patternMsg}")
    private String email;

    private Boolean isConfirm;

    private Boolean isDeleted;

    private RoleEnum role;
}
