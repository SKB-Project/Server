package com.project.skb.post.response;

import com.project.skb.post.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetPostResponseDto {

    private Long postId;
    private String type;
    private String title;
    private String content;
    private LocalDateTime dateTime;
    private Long viewCount; // 게시글 정보 response시 조회수를 넘겨 리액트에서 보이도록 하기 위해 변수 선언

    public GetPostResponseDto(Post post){
        this.postId = post.getPostId();
        this.type = post.getType();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.dateTime = post.getDateTime();
        this.viewCount = post.getViewCount();
    }

    @Builder
    public GetPostResponseDto(Long postId, String type, String title, String content,LocalDateTime dateTime,Long viewCount){
        this.postId = postId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        this.viewCount = viewCount;
    }

}
