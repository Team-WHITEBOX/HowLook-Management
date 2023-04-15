package org.whitebox.howlook.management.domain.report.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Post {
    @Id
    private Long postId;       //게시글 id

    private String member;      //원래 Member member이나 이름만 넘어오면되니 String으로 설정

    @Column(columnDefinition = "INT default 0")
    private Long photoCount;      //업로드한 사진 개수

    @Column(columnDefinition = "INT default 0")
    private Long likeCount;     //좋아요개수

    @Column(columnDefinition = "INT default 0")
    private Long postReplyCount;  //댓글개수

    @Column(columnDefinition = "INT default 0")
    private Long viewCount;       //조회수

    private String content;     //내용

    // postId를 통해 사진을 가져오는 get Method가 구현되어서 엔티티 구조 변경
    private String mainPhotoPath; //사진 경로

    @ElementCollection
    //수정필요
    //private List<String> uploads = new ArrayList<>();
    private List<String> uploads = new ArrayList<>();

    private float latitude; // 위도
    private float longitude; // 경도

    @OneToOne
    private Hashtag hashtag;
}
