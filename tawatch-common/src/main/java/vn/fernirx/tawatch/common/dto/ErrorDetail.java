package vn.fernirx.tawatch.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        name = "ErrorDetail",
        title = "Detailed Error Information",
        description = "Provides detailed information about a specific error, typically for validation failures or field-level errors"
)
public class ErrorDetail {
    @Schema(description = "The name of the field or parameter that caused the error")
    private String field;


    @Schema(description = "Human-readable error message describing the issue with the field")
    private String message;

    public static ErrorDetail of(String message, String field) {
        return ErrorDetail.builder()
                .message(message)
                .field(field)
                .build();
    }
}