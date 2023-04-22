package org.whitebox.howlook.management.domain.report.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.whitebox.howlook.management.domain.report.dto.LoginDTO;
import org.whitebox.howlook.management.domain.report.dto.RTestDTO;
import org.whitebox.howlook.management.domain.report.dto.ReportDTO;
import org.whitebox.howlook.management.domain.report.entity.RTest;
import org.whitebox.howlook.management.domain.report.repository.RTestRepository;
import org.whitebox.howlook.management.domain.report.service.LoginService;
import org.whitebox.howlook.management.domain.report.service.ReportPostService;
import org.whitebox.howlook.management.global.result.ResultCode;
import reactor.core.publisher.Mono;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequestMapping("/report")
@Log4j2
@RequiredArgsConstructor
public class ReportController {
    private final ReportPostService reportPostService;
    private final RTestRepository rTestRepository;
    private final LoginService loginSevie;
    //private String token;

    @ApiOperation(value="login")
    @GetMapping("/login")
    public ResponseEntity<String> getAccessToken(String ID, String PW){
        return loginSevie.loginThatServer(ID, PW);
    }

    //ReportDTO받는 controller
    @PostMapping(value = "/reportPost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultCode> reportPost(@RequestBody ReportDTO reportDTO) {
        reportPostService.reportPost(reportDTO);

        return ResponseEntity.ok(ResultCode.REPORT_POST_SUCCESS);
    }

    //Post지우는 Controller
    @PostMapping("/deletePost")
    public ResponseEntity<ResultCode> deletePost(Long postId, String accessToken) throws JsonProcessingException {
        System.out.println(reportPostService.deletePost(postId, accessToken).getBody());

        return ResponseEntity.ok(ResultCode.REPORT_POST_SUCCESS);
    }

    @GetMapping("/getReport")
    public ResponseEntity<ResultCode> getReport() {
        //report정보 반환 시 고려사항
        //1. POST정보를 pagination - PHOTO, HASHTAG포함
        //2. 1.을 포함한 Report와 ReportCount정보 함께 넣어서 하나의 DTO로 반환.

        return ResponseEntity.ok(ResultCode.REPORT_POST_SUCCESS);
    }

    //-----------------------//

    @Autowired
    @PostMapping(value = "/rpost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createRTest(@RequestBody RTestDTO rTestDTO) {
        // RTestDTO를 RTest Entity에 매핑한다.
        RTest rTest = new RTest();
        rTest.setName(rTestDTO.getName());
        rTest.setMemberId(rTestDTO.getMemberId());
        rTest.setGuitar(rTestDTO.getGuitar());

        // RTest Entity를 DB에 저장한다.
        rTestRepository.save(rTest);

        return ResponseEntity.ok("Just ok");
    }
}