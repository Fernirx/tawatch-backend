package vn.fernirx.tawatch.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import vn.fernirx.tawatch.common.annotation.validation.ValidEnum;
import vn.fernirx.tawatch.common.enums.UserRole;

@Data
public class UserRequest {

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    private String password;

    @NotNull
    @ValidEnum(enumClass = UserRole.class)
    private UserRole role;

    private Boolean isActive = true;

    private Boolean isVerified = false;
}