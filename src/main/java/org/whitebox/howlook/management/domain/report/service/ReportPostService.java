package org.whitebox.howlook.management.domain.report.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.whitebox.howlook.management.domain.report.dto.ReportDTO;

import java.util.List;

public interface ReportPostService {
    void reportPost(ReportDTO reportDTO);

    ResponseEntity<String> deletePost(Long postId, String accessToken) throws JsonProcessingException;
}
