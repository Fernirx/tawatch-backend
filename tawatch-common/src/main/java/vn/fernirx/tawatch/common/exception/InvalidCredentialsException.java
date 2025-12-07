package vn.fernirx.tawatch.common.exception;

import lombok.Getter;
import vn.fernirx.tawatch.common.constant.ResponseMessages;
import vn.fernirx.tawatch.common.enums.ErrorCode;

@Getter
public class InvalidCredentialsException extends SecurityBaseException {

    public InvalidCredentialsException() {
        super(ErrorCode.INVALID_CREDENTIALS, ResponseMessages.Error.INVALID_CREDENTIALS);
    }
}