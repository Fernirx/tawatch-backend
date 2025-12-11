package vn.fernirx.tawatch.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import vn.fernirx.tawatch.common.enums.UserRole;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Response DTO cho user")
public class UserResponse {
    private Long id;
    private String email;
    private UserRole role;
    private Boolean isVerified;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
    private ProfileResponse profile;
}