package vn.fernirx.tawatch.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        name = "PageResponse",
        title = "Paginated Response",
        description = "Standard paginated response wrapper containing list results along with pagination metadata"
)
public class PageResponse<T> {

    @Schema(description = "List of items for the current page")
    private List<T> content;

    @Schema(description = "Current page number (0-based index)")
    private int page;

    @Schema(description = "Number of items per page")
    private int size;

    @Schema(description = "Total number of elements across all pages")
    private long totalElements;

    @Schema(description = "Total number of pages based on totalElements and size")
    private int totalPages;

    public static <T> PageResponse<T> of(Page<T> page) {
        return PageResponse.<T>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}