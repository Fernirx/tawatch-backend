package vn.fernirx.tawatch.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Schema(description = "Response DTO cho profile")
public class ProfileResponse {
    private String firstName;
    private String lastName;
    private String fullName;
    private String phone;
    private String avatarUrl;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private Integer age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}