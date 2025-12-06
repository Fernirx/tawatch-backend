package vn.fernirx.tawatch.common.constant;

public final class SecurityConstants {

    /* ================== JWT ================== */
    public static final String JWT_ACCESS_TOKEN = "access_token";
    public static final String JWT_REFRESH_TOKEN = "refresh_token";
    public static final String JWT_RESET_PASSWORD_TOKEN = "reset_password_token";
    public static final String JWT_CLAIMS_TYPE = "type";
    public static final String JWT_CLAIMS_USERNAME = "username";
    public static final String JWT_CLAIMS_AUTHORITIES = "authorities";

    /* ================== HTTP HEADERS ================== */
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "Bearer";
    public static final int BEARER_PREFIX_LENGTH = 7;
    public static final String CONTENT_TYPE_JSON = "application/json";

    private SecurityConstants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }
}
