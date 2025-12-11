package vn.fernirx.tawatch.infrastructure.properties;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.List;

@Data
@Validated
@ConfigurationProperties(prefix = "application.cors")
public class CorsProperties {

    /** Allowed origins for CORS requests */
    @NotEmpty
    private List<String> allowedOrigins = List.of("https://yourdomain");

    /** Allowed HTTP methods for CORS requests */
    @NotEmpty
    private List<String> allowedMethods = List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH");

    /** Allowed headers in CORS requests */
    @NotEmpty
    private List<String> allowedHeaders = List.of("*");

    /** Allow credentials (cookies, authorization headers) in CORS requests */
    private Boolean allowCredentials = true;

    /** Maximum age for caching CORS preflight requests */
    private Duration maxAge = Duration.parse("PT1H"); // default PT1H
}