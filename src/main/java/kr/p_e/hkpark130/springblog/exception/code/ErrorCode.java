package kr.p_e.hkpark130.springblog.exception.code;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 400 ~ 499
    USER_NOT_FOUND(404, "USER_001", "사용자를 찾을 수 없습니다."),
    POST_NOT_FOUND(404, "POST_001", "게시글이 존재하지 않습니다."),
    COMMENT_NOT_FOUND(404, "COMMENT_001", "댓글이 존재하지 않습니다."),
    UNAUTHORIZED_UPDATE(403, "POST_002", "글을 수정할 권한이 없습니다."),
    UNAUTHORIZED_DELETE(403, "POST_003", "글을 삭제할 권한이 없습니다."),
    PASSWORD_REQUIRED(400, "COMMENT_002", "비밀번호가 필요합니다."),
    INVALID_COMMENT_PASSWORD(403, "COMMENT_003", "댓글 비밀번호가 일치하지 않습니다."),
    UNAUTHORIZED_COMMENT_ACTION(403, "COMMENT_004", "댓글에 대한 권한이 없습니다."),

    CATEGORY_NOT_FOUND(404, "CATEGORY_001", "해당 카테고리를 찾을 수 없습니다."),

    // 500 ~
    INTERNAL_SERVER_ERROR(500, "COMMON_001", "서버 내부 오류입니다.");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
