package com.project.skb.post.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPostResponseDto {

    private String type;
    private String title;
    private String content;

    @Builder
    public GetPostResponseDto(String type, String title, String content){
        this.type = type;
        this.title = title;
        this.content = content;
    }

}
