package kr.p_e.hkpark130.springblog.exception.handler;

import kr.p_e.hkpark130.springblog.exception.CustomException;
import kr.p_e.hkpark130.springblog.exception.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        log.error("[CustomException] 발생: {}", ex.getMessage());

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", ex.getErrorCode().getStatus());
        errorResponse.put("error", ex.getErrorCode().getMessage());
        errorResponse.put("code", ex.getErrorCode().getCode());

        return ResponseEntity
                .status(ex.getErrorCode().getStatus())
                .body(errorResponse);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleGeneralException(Exception ex) {
//        log.error("[Exception] 서버 에러 발생", ex);
//
//        Map<String, Object> errorResponse = new HashMap<>();
//        errorResponse.put("timestamp", LocalDateTime.now());
//        errorResponse.put("status", ErrorCode.INTERNAL_SERVER_ERROR.getStatus());
//        errorResponse.put("error", ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
//        errorResponse.put("code", ErrorCode.INTERNAL_SERVER_ERROR.getCode());
//
//        return ResponseEntity
//                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
//                .body(errorResponse);
//    }
}