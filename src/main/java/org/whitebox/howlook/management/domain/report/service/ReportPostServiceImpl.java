package org.whitebox.howlook.management.domain.report.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.whitebox.howlook.management.domain.report.dto.HashtagDTO;
import org.whitebox.howlook.management.domain.report.dto.LoginDTO;
import org.whitebox.howlook.management.domain.report.dto.PhotoDTO;
import org.whitebox.howlook.management.domain.report.dto.ReportDTO;
import org.whitebox.howlook.management.domain.report.entity.*;
import org.whitebox.howlook.management.domain.report.repository.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Transactional
@Data
@Service
public class ReportPostServiceImpl implements ReportPostService{
    private final ReportCountRepository reportCountRepository;
    private final PostRepository postRepository;
    private final ReportRepository reportRepository;
    private final HashtagRepository hashtagRepository;
    private final PhotoRepository photoRepository;
    private final ModelMapper modelMapper;


    public void reportPost(ReportDTO reportDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ReportCount reportCount = reportCountRepository.findByPostId(reportDTO.getPostId());
        if(reportCount != null) {
            Long count = reportCount.getReportCount();
            reportCount.setReportCount(++count);
        }else {
            reportCount = modelMapper.map(reportDTO, ReportCount.class);
            reportCount.setReportCount(1L);
        }
        reportCountRepository.save(reportCount);
        Report report = modelMapper.map(reportDTO, Report.class);
        reportRepository.save(report);

        if(postRepository.findPostIdByReportPostId(reportDTO.getPostId()) != null) return;

        Post post = modelMapper.map(reportDTO, Post.class);

        HashtagDTO hashtagDTO = reportDTO.getHashtagDTO();
        Hashtag hashtag = modelMapper.map(hashtagDTO, Hashtag.class);
        post.setHashtag(hashtag);

        for (int i = 0; i < reportDTO.getPhotoDTOs().size(); i++) {
            PhotoDTO photoDTO = reportDTO.getPhotoDTOs().get(i);
            Photo photo = modelMapper.map(photoDTO, Photo.class);
            photo.setPostId(reportDTO.getPostId());
            photoRepository.save(photo);
        }

        hashtagRepository.save(hashtag);

        postRepository.save(post);
    }

    public ResponseEntity<String> deletePost(Long postId, String accessToken) throws JsonProcessingException {
        //String accessToken =

        WebClient webClient = WebClient.builder()
                .baseUrl("http://192.168.0.2:9090")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .build();

        return webClient.method(HttpMethod.DELETE)
                .uri("/post/" + postId)
                //.accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class)
                .block();
    }
}
