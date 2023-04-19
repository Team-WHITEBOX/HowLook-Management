package org.whitebox.howlook.management.domain.report.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.whitebox.howlook.management.domain.report.dto.ReportDTO;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class ReportCount {
    @Id
    private Long postId;

    private Long reportCount;

    public void reportCountUp() {
        this.reportCount++;
    }

    public void reportCountInit(ReportDTO reportDTO) {
        this.postId = reportDTO.getPostId();
        this.reportCount = 1L;
    }
}
