package vn.fernirx.tawatch.common.exception;

import lombok.Getter;
import vn.fernirx.tawatch.common.constant.ResponseMessages;
import vn.fernirx.tawatch.common.enums.ErrorCode;

@Getter
public class OtpException extends AppException {

    public OtpException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static OtpException validationFailed() {
        return new OtpException(ErrorCode.OTP_VALIDATION_FAILED, ResponseMessages.Error.OTP_VALIDATION_FAILED);
    }

    public static OtpException maxAttemptsExceeded() {
        return new OtpException(ErrorCode.OTP_MAX_ATTEMPTS_EXCEED, ResponseMessages.Error.OTP_MAX_ATTEMPTS_EXCEED);
    }

    public static OtpException maxResendExceeded() {
        return new OtpException(ErrorCode.OTP_MAX_RESEND_EXCEEDED, ResponseMessages.Error.OTP_MAX_RESEND_EXCEEDED);
    }

    public static OtpException resendCooldownExceeded() {
        return new OtpException(ErrorCode.OTP_RESEND_COOLDOWN, ResponseMessages.Error.OTP_RESEND_COOLDOWN);
    }
}