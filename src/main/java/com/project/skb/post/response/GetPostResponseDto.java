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

    public GetPostResponseDto(Post post){
        this.postId = post.getPostId();
        this.type = post.getType();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.dateTime = post.getDateTime();
    }

    @Builder
    public GetPostResponseDto(Long postId, String type, String title, String content,LocalDateTime dateTime){
        this.postId = postId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
    }

}
