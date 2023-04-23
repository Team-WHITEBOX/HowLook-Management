package org.whitebox.howlook.management.domain.report.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.whitebox.howlook.management.domain.report.entity.Post;
import org.whitebox.howlook.management.domain.report.entity.Report;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportReaderDTO {
    private Long reportId;

    private String reporterId;

    private Post post;

    @QueryProjection
    public ReportReaderDTO(Report report) {
        this.reportId = report.getReportId();
        this.reporterId = report.getReporterId();
        this.post = report.getPost();
    }
}
