package vn.fernirx.tawatch.infrastructure.config;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheManagerConfig {

    private final Cache<@NonNull Object, Object> otpCache;

    public CacheManagerConfig(
            @Qualifier("otpCache") Cache<@NonNull Object, Object> otpCache
    ) {
        this.otpCache = otpCache;
    }

    @Bean
    public CacheManager otpCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.registerCustomCache("otps", otpCache);
        return cacheManager;
    }
}
