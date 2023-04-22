package org.whitebox.howlook.management.domain.report.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.whitebox.howlook.management.domain.report.dto.ReportDTO;

public interface ReportPostService {
    void reportPost(ReportDTO reportDTO);

    ResponseEntity<String> deletePost(Long postId, String accessToken) throws JsonProcessingException;
}
