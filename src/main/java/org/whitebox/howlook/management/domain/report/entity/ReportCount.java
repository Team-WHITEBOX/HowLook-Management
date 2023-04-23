package org.whitebox.howlook.management.domain.report.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

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
}
