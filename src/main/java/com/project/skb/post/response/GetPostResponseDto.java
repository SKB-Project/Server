package com.project.skb.post.response;

import com.project.skb.post.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPostResponseDto {

    private String type;
    private String title;
    private String content;

    public GetPostResponseDto(Post post){
        this.type = post.getType();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    @Builder
    public GetPostResponseDto(String type, String title, String content){
        this.type = type;
        this.title = title;
        this.content = content;
    }

}
