package vn.fernirx.tawatch.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import vn.fernirx.tawatch.common.annotation.validation.ValidPhone;
import vn.fernirx.tawatch.common.constant.ValidationConstants;

@Data
@Schema(description = "Request DTO cho địa chỉ")
public class AddressRequest {

    @NotBlank
    @Size(max = 200)
    private String recipientName;

    @NotBlank
    @ValidPhone
    @Size(max = 15)
    private String phone;

    @NotBlank
    @Size(max = 255)
    private String street;

    @NotBlank
    @Size(max = 100)
    private String ward;

    @NotBlank
    @Size(max = 100)
    private String city;

    @Pattern(regexp = ValidationConstants.Patterns.POSTAL_CODE)
    @Size(min = 6, max = 6)
    private String postalCode;

    private String country = "Việt Nam";

    @NotNull
    private Boolean isDefault = false;
}