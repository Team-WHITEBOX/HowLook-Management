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
    private String token;

    @ApiOperation(value="login")
    @GetMapping("/login")
    public ResponseEntity<String> getAccessToken(String ID, String PW) throws JsonProcessingException {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setMemberId(ID);
        loginDTO.setMemberPassword(PW);

        WebClient webClient = WebClient.builder()
                .baseUrl("http://192.168.0.2:9090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<String> tokenBody =
                webClient.post().uri(uriBuilder -> uriBuilder.path("/account/generateToken")
                                .build())
                                .bodyValue(loginDTO)
                                .retrieve()
                                .toEntity(String.class)
                                .block();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(tokenBody.getBody());

        token = jsonNode.get("accessToken").asText();

        return tokenBody;
    }


    //ReportDTO받는 controller
    @PostMapping(value = "/reportPost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultCode> reportPost(@RequestBody ReportDTO reportDTO) {
        reportPostService.reportPost(reportDTO);

        return ResponseEntity.ok(ResultCode.REPORT_POST_SUCCESS);
    }

    @PostMapping("/deletePost")
    public ResponseEntity<ResultCode> deletePost(Long postId) throws JsonProcessingException {
        System.out.println(reportPostService.deletePost(postId, token).getBody());

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