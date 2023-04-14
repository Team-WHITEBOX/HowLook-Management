package org.whitebox.howlook.management.domain.report.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;       //게시글 id

    @Column
    private String member;      //원래 Member member이나 이름만 넘어오면되니 String으로 설정

    @Column(columnDefinition = "INT default 0")
    private Long photoCount;      //업로드한 사진 개수

    @Column(columnDefinition = "INT default 0")
    private Long likeCount;     //좋아요개수

    @Column(columnDefinition = "INT default 0")
    private Long postReplyCount;  //댓글개수

    @Column(columnDefinition = "INT default 0")
    private Long viewCount;       //조회수

    private String Content;     //내용

    // postId를 통해 사진을 가져오는 get Method가 구현되어서 엔티티 구조 변경
    private String mainPhotoPath; //사진 경로

    @Column
    //수정필요
    //private List<String> uploads = new ArrayList<>();
    private String uploads;

    private float latitude; // 위도
    private float longitude; // 경도

    @OneToOne
    private Hashtag hashtag;
}
