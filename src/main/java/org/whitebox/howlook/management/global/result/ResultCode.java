package org.whitebox.howlook.management.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    // Report
    REPORT_POST_SUCCESS(200, "R001", "Post 신고 성공하였습니다."),
    DELETE_REPORT_AND_POST_SUCCESS(200, "R003", "신고되었더 게시글과 신고내역 삭제에 성공했습니다."),
    DELETE_REPORT_SUCCESS(200, "R004", "신고반려, 신고에 대한 내용만 삭제에 성공했습니다.");

    private final int status;
    private final String code;
    private final String message;
}
