package vn.fernirx.tawatch.infrastructure.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.fernirx.tawatch.common.dto.ErrorResponse;
import vn.fernirx.tawatch.common.enums.ErrorCode;
import vn.fernirx.tawatch.common.exception.AccountDisabledException;
import vn.fernirx.tawatch.common.exception.InvalidCredentialsException;
import vn.fernirx.tawatch.common.exception.SecurityBaseException;

@Slf4j
@ControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler({
            InvalidCredentialsException.class,
            AccountDisabledException.class
    })
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
            SecurityBaseException ex,
            HttpServletRequest request) {
        log.warn("[SecurityException] {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return buildErrorResponse(ex, request);
    }

    public ResponseEntity<ErrorResponse> buildErrorResponse(
            SecurityBaseException ex,
            HttpServletRequest request) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(
                errorCode,
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }
}
