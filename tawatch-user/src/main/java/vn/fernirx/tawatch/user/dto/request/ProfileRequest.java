package vn.fernirx.tawatch.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import vn.fernirx.tawatch.common.annotation.validation.ValidPhone;

import java.time.LocalDate;

@Data
@Schema(description = "Request DTO cho profile")
public class ProfileRequest {

    @NotBlank
    @Size(max = 100)
    private String firstName;

    @Size(max = 100)
    private String lastName;

    @ValidPhone
    @Size(max = 15)
    private String phone;

    private String avatarUrl;

    @Past(message = "Ngày sinh phải trong quá khứ")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
}