package org.whitebox.howlook.management.domain.report.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.whitebox.howlook.management.domain.report.dto.ReportDTO;
import org.whitebox.howlook.management.domain.report.entity.Report;
import org.whitebox.howlook.management.domain.report.repository.ReportRepository;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
@Data
public class ReportPostServiceImpl implements ReportPostService{
    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;
    public void report(ReportDTO reportDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Report report = modelMapper.map(reportDTO, Report.class);

        report.setHashtag(reportDTO);
        report.setPhoto(reportDTO);

        reportRepository.save(report);
    }
}
