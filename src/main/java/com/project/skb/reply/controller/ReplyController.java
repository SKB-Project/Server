package com.project.skb.reply.controller;


import com.project.skb.ResponseDto;
import com.project.skb.reply.request.ReplyRequestDto;
import com.project.skb.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/reply/create")
    public ResponseDto createReply(ServletRequest request, @Valid @RequestBody ReplyRequestDto replyRequestDto){
        return replyService.createReply(request, replyRequestDto);
    }
    @GetMapping("/replies/{postId}")
    public ResponseDto getReplies(ServletRequest request, @PathVariable Long postId,
                                @PageableDefault(size=20, sort="postId", direction= Sort.Direction.DESC) Pageable pageable) {
        return replyService.getReplies(request, postId, pageable);
    }
    @DeleteMapping("/reply/{replyId}/delete")
    public ResponseDto deleteReply(ServletRequest request, @PathVariable Long replyId){
        return replyService.deleteReply(request, replyId);
    }

}
