package org.whitebox.howlook.management.domain.report.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReportPageDTO {
    private List<ReportReaderDTO> content;

    private int totalPages;

    private int number;

    public ReportPageDTO(Page<ReportReaderDTO> page) {
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.number = page.getNumber();
    }
}
