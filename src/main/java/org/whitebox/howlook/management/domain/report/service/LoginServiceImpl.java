package org.whitebox.howlook.management.domain.report.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.whitebox.howlook.management.domain.report.dto.LoginDTO;

@Log4j2
@RequiredArgsConstructor
@Transactional
@Data
@Service
public class LoginServiceImpl implements LoginService{
    @Value("${MAINSERVER_URL}")
    private String mainServer;

    private String parseToken(ResponseEntity<String> tokenBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(tokenBody.getBody());

        //toeknBody에 받아온 accessToken과 refreshToken중 accessToken을 parse
        return jsonNode.get("accessToken").asText();
    }

    public ResponseEntity<String> loginThatServer(String ID, String PW){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setMemberId(ID);
        loginDTO.setMemberPassword(PW);

        WebClient webClient = WebClient.builder()
                .baseUrl("http://" + mainServer)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        //tokenBody에 token을 받아옴
        ResponseEntity<String> tokenBody =
                webClient.post().uri(uriBuilder -> uriBuilder.path("/account/generateToken")
                                .build())
                        .bodyValue(loginDTO)
                        .retrieve()
                        .toEntity(String.class)
                        .block();

        return tokenBody;
    }
}
