package vn.fernirx.tawatch.infrastructure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "application.oauth2")
public class OAuth2Properties {

    /** Configuration for redirect behavior after successful or failed OAuth2 login */
    private Redirect redirect = new Redirect();

    @Data
    public static class Redirect {

        /**
         * The frontend URL that the backend will redirect to
         * after a successful OAuth2 login, with the JWT token appended.
         *
         * Example:
         *   https://frontend.com/oauth2/success
         */
        private String successUrl = "http://localhost:5173/oauth2/success";

        /**
         * The frontend URL that the backend will redirect to
         * when OAuth2 login fails, with error message appended.
         *
         * Example:
         *   https://frontend.com/oauth2/error
         */
        private String failureUrl = "http://localhost:5173/oauth2/error";
    }
}
