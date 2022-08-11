package com.project.skb.reply.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    List<Reply> findByPostId(Long postId, Pageable pageable);
}
