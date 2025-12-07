package vn.fernirx.tawatch.common.exception;

import lombok.Getter;
import vn.fernirx.tawatch.common.enums.ErrorCode;

@Getter
public class SecurityBaseException extends RuntimeException {
    private final ErrorCode errorCode;

    public SecurityBaseException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}