package vn.fernirx.tawatch.common.exception;

import lombok.Getter;
import vn.fernirx.tawatch.common.constant.ResponseMessages;
import vn.fernirx.tawatch.common.enums.ErrorCode;

@Getter
public class ResourceAlreadyExistsException extends AppException {
    private final String resourceName;

    public ResourceAlreadyExistsException(String resourceName) {
        super(ErrorCode.ALREADY_EXISTS, String.format(ResponseMessages.Error.ALREADY_EXISTS, resourceName));
        this.resourceName = resourceName;
    }
}