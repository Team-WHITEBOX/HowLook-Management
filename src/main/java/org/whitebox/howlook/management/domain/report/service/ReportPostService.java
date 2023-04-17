package org.whitebox.howlook.management.domain.report.service;

import org.springframework.stereotype.Service;
import org.whitebox.howlook.management.domain.report.dto.ReportDTO;

public interface ReportPostService {
    void reportPost(ReportDTO reportDTO);
}
