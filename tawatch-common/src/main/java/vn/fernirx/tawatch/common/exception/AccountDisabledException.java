package vn.fernirx.tawatch.common.exception;

import lombok.Getter;
import vn.fernirx.tawatch.common.constant.ResponseMessages;
import vn.fernirx.tawatch.common.enums.ErrorCode;

@Getter
public class AccountDisabledException extends SecurityBaseException {

    public AccountDisabledException() {
        super(ErrorCode.ACCOUNT_DISABLED, ResponseMessages.Error.ACCOUNT_DISABLED);
    }
}