package vn.fernirx.tawatch.common.exception;

import lombok.Getter;
import vn.fernirx.tawatch.common.constant.ResponseMessages;
import vn.fernirx.tawatch.common.enums.ErrorCode;

@Getter
public class ResourceInUseException extends AppException {
    private final String resourceName;

    public ResourceInUseException(String resourceName) {
        super(ErrorCode.IN_USE, String.format(ResponseMessages.Error.IN_USE, resourceName));
        this.resourceName = resourceName;
    }
}