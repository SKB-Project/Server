package com.project.skb.post.repository;

import com.project.skb.post.domain.Post;
import com.project.skb.post.domain.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getPosts(int page){
        return jpaQueryFactory.selectFrom(QPost.post)
                .limit(10)
                .offset((long) (page -1) * 10)
                .orderBy(QPost.post.postId.desc())
                .fetch();
    }
}
