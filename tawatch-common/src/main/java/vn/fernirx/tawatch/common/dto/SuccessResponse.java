package vn.fernirx.tawatch.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.fernirx.tawatch.common.constant.ValidationConstants;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        name = "SuccessResponse",
        title = "Success Response",
        description = "Standard success response wrapper for all successful API operations, containing optional data payload"
)
public class SuccessResponse<T> {
    @Schema(description = "Human-readable success message describing the completed operation")
    private String message;

    @Schema(description = "Response payload containing the requested data. Can be any type or null for operations without return data")
    private T data;

    @Schema(description = "ISO 8601 timestamp indicating when the successful response was generated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ValidationConstants.Patterns.ISO_ZONED_DATE_TIME)
    private ZonedDateTime timestamp;

    public static <T> SuccessResponse<T> of(String message, T data) {
        return SuccessResponse.<T>builder()
                .timestamp(ZonedDateTime.now())
                .message(message)
                .data(data)
                .build();
    }

    public static SuccessResponse<Void> of(String message) {
        return SuccessResponse.<Void>builder()
                .timestamp(ZonedDateTime.now())
                .message(message)
                .build();
    }
}