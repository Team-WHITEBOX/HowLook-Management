package org.whitebox.howlook.management.domain.report.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.whitebox.howlook.management.domain.report.dto.ReportDTO;
import org.whitebox.howlook.management.domain.report.dto.ReportPageDTO;
import org.whitebox.howlook.management.domain.report.dto.ReportReaderDTO;
import org.whitebox.howlook.management.domain.report.service.LoginService;
import org.whitebox.howlook.management.domain.report.service.ReportPostService;
import org.whitebox.howlook.management.global.result.ResultCode;


//REPORT_SERVER_URL:PORT/report
//Post mapping 으로 보내는 경우 신고정보 받음
//Get mapping 으로 보내는 경우 신고정보 반환
//Delete mapping 으로 보내는 경우 howlook backend 게시글 지움.
@RestController
@RequestMapping("/report")
@Log4j2
@RequiredArgsConstructor
public class ReportController {
    private final ReportPostService reportPostService;
    private final LoginService loginService;
    //private String token;

    @ApiOperation(value="login with howlook-backend server")
    @GetMapping("/login")
    public ResponseEntity<String> getAccessToken(String ID, String PW){
        return loginService.loginThatServer(ID, PW);
    }

    //ReportDTO받는 controller
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultCode> reportPost(@RequestBody ReportDTO reportDTO) {
        reportPostService.reportPost(reportDTO);

        return ResponseEntity.ok(ResultCode.REPORT_POST_SUCCESS);
    }

    @ApiOperation(value = "최근 신고 10개 조회 Long reportId, String reporterId, Post post 포함.")
    @GetMapping
    public ResponseEntity<ReportPageDTO> getRecent10Reports(int page) {
        //size 는 10으로 고정.
        final Page<ReportReaderDTO> reportList = reportPostService.getReportPage(10, page);
        final ReportPageDTO reportPageDTO = new ReportPageDTO(reportList);

        return ResponseEntity.ok(reportPageDTO);
    }

    //Post 지우는 Controller
    @ApiOperation(value = "신고 수렴(게시글 삭제)")
    @DeleteMapping("/accept")
    public ResponseEntity<ResultCode> deletePost(Long postId, String accessToken) throws JsonProcessingException {
        reportPostService.deletePost(postId, accessToken);

        return ResponseEntity.ok(ResultCode.DELETE_REPORT_AND_POST_SUCCESS);
    }

    @ApiOperation(value = "신고 반려(신고내역만 삭제)")
    @DeleteMapping("/reject")
    public ResponseEntity<ResultCode> deletePost(Long postId) {
        reportPostService.rejectReport(postId);

        return ResponseEntity.ok(ResultCode.DELETE_REPORT_SUCCESS);
    }
}