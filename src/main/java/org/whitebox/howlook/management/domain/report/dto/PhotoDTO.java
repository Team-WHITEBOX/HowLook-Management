package org.whitebox.howlook.management.domain.report.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PhotoDTO {
    String path;
    Long photoId;
}
