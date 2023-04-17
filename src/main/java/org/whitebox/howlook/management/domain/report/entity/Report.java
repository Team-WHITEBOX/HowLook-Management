package org.whitebox.howlook.management.domain.report.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.whitebox.howlook.management.domain.report.dto.ReportDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamicInsert
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private Long postId;

    private String memberId;      //원래 Member member이나 이름만 넘어오면되니 String으로 설정

    private Long photoCount;      //업로드한 사진 개수

    private Long likeCount;     //좋아요개수

    private Long postReplyCount;  //댓글개수

    private Long viewCount;       //조회수

    private String content;     //내용

    // postId를 통해 사진을 가져오는 get Method가 구현되어서 엔티티 구조 변경
    private String mainPhotoPath; //사진 경로

    @OneToMany(mappedBy = "postId")
    //수정필요
    private List<Photo> PhotoDTOs = new ArrayList<>();

    private LocalDateTime registrationDate;

    private LocalDateTime modificationDate;

    @OneToOne
    private Hashtag hashtag;
}
