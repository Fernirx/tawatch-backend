package vn.fernirx.tawatch.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request DTO cho guest session")
public class GuestSessionRequest {
    private String guestToken;
}