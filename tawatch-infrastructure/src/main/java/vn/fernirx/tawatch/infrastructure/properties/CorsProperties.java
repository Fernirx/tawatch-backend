package vn.fernirx.tawatch.infrastructure.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Data
@Validated
@ConfigurationProperties(prefix = "application.cors")
public class CorsProperties {

    /** Allowed origins for CORS requests (comma-separated URLs) */
    @NotBlank
    private String allowedOrigins = "https://yourdomain";

    /** Allowed HTTP methods for CORS requests (comma-separated) */
    @NotBlank
    private String allowedMethods = "GET,POST,PUT,DELETE,OPTIONS,PATCH";

    /** Allowed headers in CORS requests (comma-separated or '*' for all) */
    @NotBlank
    private String allowedHeaders = "*";

    /** Allow credentials (cookies, authorization headers) in CORS requests */
    private Boolean allowCredentials = true;

    /** Maximum age for caching CORS preflight requests */
    private Duration maxAge = Duration.parse("PT1H"); // default PT1H
}