package vn.fernirx.tawatch.infrastructure.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import vn.fernirx.tawatch.common.constant.ResponseMessages;
import vn.fernirx.tawatch.common.dto.ErrorDetail;
import vn.fernirx.tawatch.common.dto.ErrorResponse;
import vn.fernirx.tawatch.common.enums.ErrorCode;
import vn.fernirx.tawatch.common.exception.AppException;
import vn.fernirx.tawatch.common.exception.OtpException;

import java.util.List;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleBusinessExceptions(
            AppException ex,
            HttpServletRequest request) {
        logException(ex);
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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request) {
        logException(ex);
        String field = ex.getName();
        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.INVALID_PARAMETER,
                String.format(ResponseMessages.Error.INVALID_PARAMETER, field),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(ErrorCode.INVALID_PARAMETER.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {
        logException(ex);
        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.MALFORMED_JSON,
                ResponseMessages.Error.MALFORMED_JSON,
                request.getRequestURI()
        );
        return ResponseEntity
                .status(ErrorCode.MALFORMED_JSON.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        logException(ex);
        List<ErrorDetail> details = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError ->
                        ErrorDetail.of(
                                Objects.requireNonNullElse(fieldError.getDefaultMessage(), "Invalid value"),
                                fieldError.getField()
                        ))
                .toList();
        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.VALIDATION_ERROR,
                ResponseMessages.Error.VALIDATION_FAILED,
                details,
                request.getRequestURI()
        );
        return ResponseEntity
                .status(ErrorCode.VALIDATION_ERROR.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(OtpException.class)
    public ResponseEntity<ErrorResponse> handleOtpException(
            OtpException ex,
            HttpServletRequest request) {
        logException(ex);
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

    private void logException(Exception ex) {
        log.error("[Global Exception] {} - {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
    }
}
