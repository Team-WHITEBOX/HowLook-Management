package org.whitebox.howlook.management.domain.report.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.whitebox.howlook.management.domain.report.dto.RTestDTO;
import org.whitebox.howlook.management.domain.report.dto.ReportDTO;
import org.whitebox.howlook.management.domain.report.entity.RTest;
import org.whitebox.howlook.management.domain.report.repository.RTestRepository;
import org.whitebox.howlook.management.domain.report.service.ReportPostService;
import org.whitebox.howlook.management.global.result.ResultCode;

@RestController
@RequestMapping("/report")
@Log4j2
@RequiredArgsConstructor
public class ReportController {

    private final ReportPostService reportPostService;
    private final RTestRepository rTestRepository;

    @PostMapping(value = "/reportpost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultCode> reportPost(@RequestBody ReportDTO reportDTO) {
        reportPostService.reportPost(reportDTO);

        return ResponseEntity.ok(ResultCode.CREATE_POST_SUCCESS);
    }

    @GetMapping(value = "/test")
    public String test() {
        return "hello!!!";
    }

    @GetMapping
    public String getName() { return "my name is floral"; }

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