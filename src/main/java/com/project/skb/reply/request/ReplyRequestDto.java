package com.project.skb.reply.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ReplyRequestDto {
    // @NotBlank(message = "존재하는 게시물인지 확인해주세요")
    private Long postId;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}
