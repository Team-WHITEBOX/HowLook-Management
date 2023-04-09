package org.whitebox.howlook.management.domain.report.controller;

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

    @PostMapping(value = "/reportPost")
    public ResponseEntity<ResultCode> reportPosts() {
        reportPostService.report();

        return ResponseEntity.ok(ResultCode.CREATE_POST_SUCCESS);
    }
}