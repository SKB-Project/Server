package com.project.skb.post.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByType(String type, Pageable pageable);
}
