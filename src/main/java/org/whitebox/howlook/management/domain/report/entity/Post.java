package org.whitebox.howlook.management.domain.report.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.whitebox.howlook.management.domain.report.dto.PhotoDTO;

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
    private Long postId;

    private String memberId;      //원래 Member member이나 이름만 넘어오면되니 String으로 설정

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

    @OneToMany(mappedBy = "postId")
    private List<Photo> uploads = new ArrayList<>();

    //private float latitude; // 위도 ->원래 post에는 있긴 한데, postReaderDTO에는 없는 정보라 일단 뺌
    //private float longitude; // 경도

    @OneToOne
    private Hashtag hashtag;

    public void setHashtag(Hashtag hashtag){
        this.hashtag = hashtag;
    }
}
