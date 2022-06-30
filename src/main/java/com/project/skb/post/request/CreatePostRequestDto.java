package com.project.skb.post.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreatePostRequestDto {

    @NotBlank(message = "스터디 유형을 선택해주세요.")
    private String type;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;


}
