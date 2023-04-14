package org.whitebox.howlook.management.domain.report.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.whitebox.howlook.management.domain.report.service.ReportPostService;
import org.whitebox.howlook.management.global.result.ResultCode;

@RestController
@RequestMapping("/report")
@Log4j2
@RequiredArgsConstructor
public class ReportController {

    private final ReportPostService reportPostService;

    @ApiOperation(value="report 시 postId를 포함해주세요.")
    @PostMapping(value = "/reportPost")
    public ResponseEntity<ResultCode> reportPosts(Long postId) {
        reportPostService.report(postId);

        return ResponseEntity.ok(ResultCode.CREATE_POST_SUCCESS);
    }
}