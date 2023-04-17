package org.whitebox.howlook.management.domain.report.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.whitebox.howlook.management.domain.report.dto.HashtagDTO;
import org.whitebox.howlook.management.domain.report.dto.ReportDTO;
import org.whitebox.howlook.management.domain.report.entity.Hashtag;
import org.whitebox.howlook.management.domain.report.entity.Report;
import org.whitebox.howlook.management.domain.report.repository.HashtagRepository;
import org.whitebox.howlook.management.domain.report.repository.ReportRepository;

@Log4j2
@RequiredArgsConstructor
@Transactional
@Data
@Service
public class ReportPostServiceImpl implements ReportPostService{
    private final ReportRepository reportRepository;
    private final HashtagRepository hashtagRepository;
    private final ModelMapper modelMapper;
    public void reportPost(ReportDTO reportDTO) {

        System.out.println("PostId:" +reportDTO.getPostId());
        System.out.println("MemberId:" +reportDTO.getMemberId());
        System.out.println("PhotoCount:" + reportDTO.getPhotoCount());
        System.out.println("LikeCount:" + reportDTO.getLikeCount());
        System.out.println("ReplyCount:" + reportDTO.getPostReplyCount());
        System.out.println("viewCount:" + reportDTO.getViewCount());
        System.out.println("Content:" + reportDTO.getContent());
        System.out.println("MainPhotoPath:" + reportDTO.getMainPhotoPath());
        System.out.println("photoDTOs:" + reportDTO.getPhotoDTOs());
        System.out.println("registrationDate:" + reportDTO.getRegistrationDate());
        System.out.println("modificationDate:" + reportDTO.getModificationDate());
        System.out.println("Hashtag:" + reportDTO.getHashtagDTO());

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Report report = modelMapper.map(reportDTO, Report.class);

        HashtagDTO hashtagDTO = reportDTO.getHashtagDTO();
        Hashtag hashtag = modelMapper.map(hashtagDTO, Hashtag.class);

        hashtagRepository.save(hashtag);

        reportRepository.save(report);
    }
}
