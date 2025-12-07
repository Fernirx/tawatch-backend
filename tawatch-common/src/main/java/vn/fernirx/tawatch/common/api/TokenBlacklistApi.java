package vn.fernirx.tawatch.common.api;

public interface TokenBlacklistApi {
    boolean isBlacklisted(String token);

    void addToBlacklist(String token);

    void removeFromBlacklist(String token);

    void clearExpiredBlacklist();
}