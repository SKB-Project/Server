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
    public List<Post> getPosts(int page){ // limit를 바꾸었으나 아직도 10개의 데이터만 옴 확인해야할 것
        return jpaQueryFactory.selectFrom(QPost.post)
                .limit(30)
                .offset((long) (page -1) * 30)
                .orderBy(QPost.post.postId.desc())
                .fetch();
    }
}
