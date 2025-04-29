package kr.p_e.hkpark130.springblog.exception;

import kr.p_e.hkpark130.springblog.exception.code.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage() + " : " + detailMessage);
        this.errorCode = errorCode;
    }
}