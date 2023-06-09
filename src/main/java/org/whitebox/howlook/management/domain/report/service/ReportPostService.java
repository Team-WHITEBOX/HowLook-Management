package org.whitebox.howlook.management.domain.report.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.whitebox.howlook.management.domain.report.dto.ReportDTO;
import org.whitebox.howlook.management.domain.report.dto.ReportReaderDTO;

public interface ReportPostService {
    void reportPost(ReportDTO reportDTO);

    ResponseEntity<String> deletePost(Long postId, String accessToken) throws JsonProcessingException;

    void rejectReport(Long postId);

    Page<ReportReaderDTO> getReportPage(int size, int page);
}
