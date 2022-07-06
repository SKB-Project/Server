package com.project.skb.post.repository;

import com.project.skb.post.domain.Post;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getPosts(int page);
}
