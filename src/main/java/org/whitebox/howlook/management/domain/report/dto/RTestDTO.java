package org.whitebox.howlook.management.domain.report.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@NoArgsConstructor
public class RTestDTO {
    private String name;

    private Long memberId;

    private String guitar;
}
