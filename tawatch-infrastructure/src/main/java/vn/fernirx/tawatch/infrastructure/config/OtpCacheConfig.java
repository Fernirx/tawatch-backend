package vn.fernirx.tawatch.infrastructure.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.fernirx.tawatch.infrastructure.properties.CacheProperties;

@Configuration
@RequiredArgsConstructor
public class OtpCacheConfig {
    private final CacheProperties cacheProperties;

    @Bean
    public Cache<@NonNull Object, @NonNull Object> otpCache() {
        CacheProperties.OtpCache otpCache = cacheProperties.getOtp();
        return Caffeine.newBuilder()
                .maximumSize(otpCache.getMaximumSize())
                .expireAfterWrite(otpCache.getExpireAfterWrite())
                .initialCapacity(otpCache.getInitialCapacity())
                .build();
    }
}
