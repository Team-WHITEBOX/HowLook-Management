package org.whitebox.howlook.management.domain.report.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@DynamicInsert
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private String reporterId;      //신고한 유저 ID

    //private Long postId;

    @OneToOne
    private Post post;

    public void setPost(Post post) {this.post = post;}
}
