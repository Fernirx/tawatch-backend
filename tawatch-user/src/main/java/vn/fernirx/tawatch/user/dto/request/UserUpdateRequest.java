package vn.fernirx.tawatch.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Request DTO cho cập nhật thông tin user")
public class UserUpdateRequest {

    @Email
    @Size(max = 100)
    private String email;

    private String password;

    @Schema(description = "Trạng thái active", example = "true")
    private Boolean isActive;
}