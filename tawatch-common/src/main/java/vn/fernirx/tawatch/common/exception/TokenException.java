package vn.fernirx.tawatch.common.exception;

import lombok.Getter;
import vn.fernirx.tawatch.common.constant.ResponseMessages;
import vn.fernirx.tawatch.common.enums.ErrorCode;
import org.springframework.security.core.AuthenticationException;

@Getter
public class TokenException extends AuthenticationException {
    private final ErrorCode errorCode;

    public TokenException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public static TokenException invalid() {
        return new TokenException(ErrorCode.TOKEN_INVALID, ResponseMessages.Error.TOKEN_INVALID);
    }

    public static TokenException expired() {
        return new TokenException(ErrorCode.TOKEN_EXPIRED, ResponseMessages.Error.TOKEN_EXPIRED);
    }
}