package vn.fernirx.tawatch.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.fernirx.tawatch.common.constant.ValidationConstants;
import vn.fernirx.tawatch.common.enums.ErrorCode;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        name = "ErrorResponse",
        title = "Error Response",
        description = "Standard wrapper for all API errors, containing a main message, error code, optional detailed errors, timestamp, and request path"
)
public class ErrorResponse {
    @Schema(description = "Human-readable error message describing what went wrong")
    private String message;

    @Schema(description = "Machine-readable error code corresponding to the type of error")
    private ErrorCode code;

    @Schema(description = "Optional list of detailed error information for debugging or validation failures")
    private List<ErrorDetail> details;

    @Schema(description = "ISO 8601 timestamp indicating when the error occurred")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ValidationConstants.Patterns.ISO_ZONED_DATE_TIME)
    private ZonedDateTime timestamp;

    @Schema(description = "The request path that triggered the error")
    private String path;

    public static ErrorResponse of(ErrorCode code, String message, List<ErrorDetail> details, String path) {
        return ErrorResponse.builder()
                .timestamp(ZonedDateTime.now())
                .code(code)
                .message(message)
                .details(details)
                .path(path)
                .build();
    }

    public static ErrorResponse of(ErrorCode code, String message, String path) {
        return of(code, message, null, path);
    }
}
