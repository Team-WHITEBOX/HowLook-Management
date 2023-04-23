package org.whitebox.howlook.management.domain.report.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.whitebox.howlook.management.domain.report.dto.ReportReaderDTO;

public interface ReportRepositoryQueryDSL {
    Page<ReportReaderDTO> findReportReaderDTOPage(Pageable pageable);
}
