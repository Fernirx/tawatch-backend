package vn.fernirx.tawatch.common.constant;

public final class ResponseMessages {

    private ResponseMessages() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }

    /* ================== SUCCESS MESSAGES ================== */
    public static final class Success {

        /* ================== GENERIC ================== */
        public static final String GENERIC = "Success";
        public static final String CREATED = "%s created successfully";
        public static final String UPDATED = "%s updated successfully";
        public static final String DELETED = "%s deleted successfully";
        public static final String RETRIEVED = "%s retrieved successfully";

        /* ================== AUTH ================== */
        public static final String LOGIN = "Login successful";
        public static final String LOGOUT = "Logout successful";
        public static final String PASSWORD_RESET = "Password reset successful";
        public static final String REFRESH_TOKEN_SUCCESS = "Token refreshed successfully";

        /* ================== OTP ================== */
        public static final String SEND_OTP_SUCCESS = "OTP sent successfully";
        public static final String OTP_VERIFY_SUCCESS = "OTP verified successfully";

        private Success() {}
    }

    /* ================== ERROR MESSAGES ================== */
    public static final class Error {

        /* ================== RESOURCE ================== */
        public static final String NOT_FOUND = "%s not found";
        public static final String ALREADY_EXISTS = "%s already exists";
        public static final String IN_USE = "%s is currently in use";

        /* ================== AUTH ================== */
        public static final String INVALID_CREDENTIALS = "Invalid credentials";
        public static final String ACCOUNT_DISABLED = "Account is disabled";
        public static final String UNAUTHORIZED = "Authentication is required to access this resource";
        public static final String ACCESS_DENIED = "Access denied";
        public static final String TOKEN_INVALID = "Invalid token";
        public static final String TOKEN_EXPIRED = "Token expired";

        /* ================== OTP ================== */
        public static final String OTP_VALIDATION_FAILED = "OTP validation failed";
        public static final String OTP_MAX_ATTEMPTS_EXCEED = "Maximum OTP attempts exceeded";
        public static final String OTP_MAX_RESEND_EXCEEDED = "Maximum OTP resend attempts exceeded";
        public static final String OTP_RESEND_COOLDOWN = "You must wait before requesting another OTP";

        /* ================== REQUEST ================== */
        public static final String MALFORMED_JSON = "Malformed JSON in request body";

        /* ================== SERVER ================== */
        public static final String INTERNAL = "Internal server error";
        public static final String SERVICE_UNAVAILABLE = "The service is currently unavailable";

        private Error() {}
    }
}