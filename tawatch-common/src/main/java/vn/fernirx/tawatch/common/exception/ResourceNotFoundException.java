package vn.fernirx.tawatch.common.exception;

import lombok.Getter;
import vn.fernirx.tawatch.common.constant.ResponseMessages;
import vn.fernirx.tawatch.common.enums.ErrorCode;

@Getter
public class ResourceNotFoundException extends AppException {
    private final String resourceName;

    public ResourceNotFoundException(String resourceName) {
        super(ErrorCode.NOT_FOUND, String.format(ResponseMessages.Error.NOT_FOUND, resourceName));
        this.resourceName = resourceName;
    }
}