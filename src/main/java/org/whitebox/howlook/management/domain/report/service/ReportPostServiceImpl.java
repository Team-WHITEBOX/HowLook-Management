package org.whitebox.howlook.management.domain.report.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.whitebox.howlook.management.domain.report.dto.HashtagDTO;
import org.whitebox.howlook.management.domain.report.dto.PhotoDTO;
import org.whitebox.howlook.management.domain.report.dto.ReportDTO;
import org.whitebox.howlook.management.domain.report.dto.ReportReaderDTO;
import org.whitebox.howlook.management.domain.report.entity.*;
import org.whitebox.howlook.management.domain.report.repository.*;

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

    @Value("${MAINSERVER_URL}")
    private String mainServer;

    public void reportPost(ReportDTO reportDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //postId의 신고회수 조회 및 count++
        ReportCount reportCount = reportCountRepository.findByPostId(reportDTO.getPostId());
        if(reportCount != null) {
            reportCount.reportCountUp();
        }else {
            reportCount = modelMapper.map(reportDTO, ReportCount.class);
            reportCount.setReportCount(1L);
        }
        reportCountRepository.save(reportCount);
        Report report = modelMapper.map(reportDTO, Report.class);

        if(postRepository.findPostIdByReportPostId(reportDTO.getPostId()) != null) {
            reportRepository.save(report);
            return;
        }

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

        report.setPost(post);
        report.setPostId(post.getPostId());
        reportRepository.save(report);
    }

    //신고내역 승낙, main server에 신고된 게시글 삭제요청 전송
    public ResponseEntity<String> deletePost(Long postId, String accessToken) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://"+ mainServer)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .build();


        postRepository.deleteById(postId);
        reportRepository.deleteReportByPostId(postId);

        return webClient.method(HttpMethod.DELETE)
                .uri("/post/" + postId)
                //.accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class)
                .block();
    }

    //신고내역 거부, 해당 서버에서 저장된 신고내역만 삭제
    public void rejectReport(Long postId) {
        postRepository.deleteById(postId);
        reportRepository.deleteReportByPostId(postId);
    }

    public Page<ReportReaderDTO> getReportPage(int size, int page) {
        final Pageable pageable = PageRequest.of(page, size);
        return reportRepository.findReportReaderDTOPage(pageable);
    }
}
