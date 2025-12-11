package vn.fernirx.tawatch.infrastructure.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import vn.fernirx.tawatch.common.constant.ResponseMessages;
import vn.fernirx.tawatch.common.constant.SecurityConstants;
import vn.fernirx.tawatch.common.dto.ErrorResponse;
import vn.fernirx.tawatch.common.enums.ErrorCode;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException {
        log.warn(
                "[SecurityException] {} - {}",
                accessDeniedException.getClass().getSimpleName(),
                accessDeniedException.getMessage()
        );
        ErrorCode errorCode = ErrorCode.ACCESS_DENIED;
        String message = ResponseMessages.Error.ACCESS_DENIED;
        int statusCode = errorCode.getHttpStatus().value();
        ErrorResponse errorResponse = ErrorResponse.of(
                errorCode,
                message,
                request.getRequestURI()
        );
        response.setContentType(SecurityConstants.CONTENT_TYPE_JSON);
        response.setStatus(statusCode);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        response.getWriter().flush();
    }
}
