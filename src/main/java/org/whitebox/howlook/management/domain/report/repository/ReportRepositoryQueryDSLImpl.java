package org.whitebox.howlook.management.domain.report.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.whitebox.howlook.management.domain.report.dto.QReportReaderDTO;
import org.whitebox.howlook.management.domain.report.dto.ReportReaderDTO;

import java.util.List;

import static org.whitebox.howlook.management.domain.report.entity.QReport.report;


@Log4j2
@RequiredArgsConstructor
public class ReportRepositoryQueryDSLImpl implements ReportRepositoryQueryDSL{
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReportReaderDTO> findReportReaderDTOPage(Pageable pageable) {
        final List<ReportReaderDTO> reportReaderDTOs = queryFactory
                .select(new QReportReaderDTO(
                        report))
                .from(report)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(report.reporterId.desc())
                .distinct()
                .fetch();
        final long total = queryFactory
                .selectFrom(report).fetch().size();
        return new PageImpl<>(reportReaderDTOs, pageable, total);
    }
}
