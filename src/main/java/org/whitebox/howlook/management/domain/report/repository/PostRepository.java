package org.whitebox.howlook.management.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.whitebox.howlook.management.domain.report.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    @Query("select f from Post f where f.postId = :reportPostId")
    Post findPostIdByReportPostId(Long reportPostId);
}
