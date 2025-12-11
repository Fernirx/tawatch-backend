package vn.fernirx.tawatch.infrastructure.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import vn.fernirx.tawatch.common.constant.SecurityConstants;
import vn.fernirx.tawatch.common.dto.ErrorResponse;
import vn.fernirx.tawatch.common.enums.ErrorCode;
import vn.fernirx.tawatch.common.exception.TokenException;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(SecurityConstants.CONTENT_TYPE_JSON);
        ErrorResponse errorResponse;
        int statusCode;

        if (authException instanceof TokenException ex) {
            ErrorCode errorCode = ex.getErrorCode();
            statusCode = errorCode.getHttpStatus().value();

            errorResponse = ErrorResponse.of(
                    errorCode,
                    ex.getMessage(),
                    request.getRequestURI()
            );
        } else {
            statusCode = HttpServletResponse.SC_UNAUTHORIZED;
            errorResponse = ErrorResponse.of(
                    ErrorCode.AUTHENTICATION_FAILED,
                    authException.getMessage(),
                    request.getRequestURI()
            );
        }

        response.setStatus(statusCode);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        response.getWriter().flush();
    }
}