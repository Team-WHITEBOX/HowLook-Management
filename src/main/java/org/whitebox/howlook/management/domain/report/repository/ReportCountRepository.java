package org.whitebox.howlook.management.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.whitebox.howlook.management.domain.report.entity.ReportCount;

public interface ReportCountRepository extends JpaRepository<ReportCount, Long> {
    @Query("select r from ReportCount r where r.postId = :postId")
    ReportCount findByPostId(Long postId);
}
