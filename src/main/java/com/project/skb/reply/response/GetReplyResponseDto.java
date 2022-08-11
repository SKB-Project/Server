package com.project.skb.reply.response;

import com.project.skb.reply.domain.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetReplyResponseDto {

    private Long replyId;
    private Long postId;
    private String content;
    private String userName; // 댓글을 생성한 user의 닉네임
    private Long userId; // 댓글을 생성한 user의 id

    public GetReplyResponseDto(Reply reply){
        this.replyId = reply.getReplyId();
        this.postId = reply.getPostId();
        this.content = reply.getContent();
        this.userName = reply.getUser().getUsername();
        this.userId = reply.getUser().getId();
    }

    @Builder
    public GetReplyResponseDto(Long replyId, Long postId, String content, String userName, Long userId){
        this.replyId = replyId;
        this.postId = postId;
        this.content = content;
        this.userName = userName;
        this.userId = userId;
    }
}
