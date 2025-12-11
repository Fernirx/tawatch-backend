package vn.fernirx.tawatch.infrastructure.properties;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Data
@Validated
@ConfigurationProperties(prefix = "application.cache")
public class CacheProperties {

    /** OTP cache configuration */
    private OtpCache otp = new OtpCache();

    @Data
    public static class OtpCache {
        /** Maximum number of OTP entries that can be stored in the cache */
        private Integer maximumSize = 10000;

        /** Time duration after which an OTP entry expires and is automatically removed */
        private Duration expireAfterWrite = Duration.parse("PT5M"); // default PT5M

        /** Maximum number of failed verification attempts allowed per OTP */
        private Integer maxAttempts = 5;

        /** Initial capacity of the cache (optimization for memory allocation) */
        private Integer initialCapacity = 100;

        /** Maximum number of times an OTP can be resent to the same user */
        private Integer maxResend = 3;

        /** Cooldown period between OTP resend requests */
        private Duration resendCooldown = Duration.parse("PT1M"); // default PT1M
    }
}