package vn.fernirx.tawatch.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Response DTO cho địa chỉ")
public class AddressResponse {
    private Long id;
    private Long userId;
    private String recipientName;
    private String phone;
    private String street;
    private String ward;
    private String city;
    private String postalCode;
    private String country;
    private String fullAddress;
    private Boolean isDefault;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
