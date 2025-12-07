package vn.fernirx.tawatch.common.constant;

public final class ValidationConstants {

    /* ================== INNER CLASS: Patterns ================== */
    public static final class Patterns {
        public static final String PHONE = "^\\+[1-9]\\d{1,14}$";
        public static final String OTP = "^\\d{6}$";
        public static final String ISO_ZONED_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssXXX";
        private Patterns() {}
    }

    private ValidationConstants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }
}
