package vn.fernirx.tawatch.common.constant;

public final class ValidationConstants {

    public static final class Patterns {
        public static final String PHONE = "^\\+[1-9]\\d{1,14}$";
        private Patterns() {}
    }

    private ValidationConstants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }
}
