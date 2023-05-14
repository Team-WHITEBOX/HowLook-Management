package org.whitebox.howlook.management.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.whitebox.howlook.management.domain.report.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long>, ReportRepositoryQueryDSL {

    @Modifying
    @Query("delete from Report rp where rp.postId = :postId")
    void deleteReportByPostId(Long postId);
}
