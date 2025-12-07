package vn.fernirx.tawatch.infrastructure.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Data
@Validated
@ConfigurationProperties(prefix = "application.security.jwt")
public class JwtProperties {

    /** JWT secret key */
    @NotBlank
    private String secret;

    /** JWT issuer */
    @NotBlank
    private String issuer;

    /** Access token configuration */
    private AccessToken access = new AccessToken();

    /** Refresh token configuration */
    private RefreshToken refresh = new RefreshToken();

    /** Reset password token configuration */
    private ResetPasswordToken resetPassword = new ResetPasswordToken();

    @Data
    public static class AccessToken {
        /** Expiration duration of access token */
        private Duration expiration = Duration.parse("PT10M"); // default PT10M
    }

    @Data
    public static class RefreshToken {
        /** Expiration duration of refresh token */
        private Duration expiration = Duration.parse("P7D"); // default P7D
    }

    @Data
    public static class ResetPasswordToken {
        /** Expiration duration of reset-password token */
        private Duration expiration = Duration.parse("PT15M"); // default PT15M
    }
}
