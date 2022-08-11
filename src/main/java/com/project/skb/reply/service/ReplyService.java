package com.project.skb.reply.service;

import com.project.skb.ResponseDto;
import com.project.skb.config.security.JwtAuthenticationProvider;
import com.project.skb.exception.domain.PostNotFoundException;
import com.project.skb.exception.domain.ReplyNotFoundException;
import com.project.skb.post.domain.Post;
import com.project.skb.post.response.GetPostResponseDto;
import com.project.skb.reply.domain.Reply;
import com.project.skb.reply.domain.ReplyRepository;
import com.project.skb.reply.request.ReplyRequestDto;
import com.project.skb.reply.response.GetReplyResponseDto;
import com.project.skb.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final ReplyRepository replyRepository;

    public ResponseDto createReply(ServletRequest request, ReplyRequestDto replyRequestDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Reply reply = Reply.builder()
                .postId(replyRequestDto.getPostId())
                .content(replyRequestDto.getContent())
                .build();

        reply.insertUser(user);
        replyRepository.save(reply);
        return new ResponseDto("SUCCESS", reply.getReplyId());

    }

    public ResponseDto getReplies(ServletRequest request, Long postId, Pageable pageable){
        List<GetReplyResponseDto> pages = replyRepository.findByPostId(postId, pageable)
                .stream()
                .map(reply -> new GetReplyResponseDto(reply))
                .collect(Collectors.toList());

        return new ResponseDto("SUCCESS",pages);
    }

    @Transactional
    public ResponseDto deleteReply(ServletRequest request, Long replyId) {

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyNotFoundException());

        replyRepository.delete(reply);

        return new ResponseDto("SUCCESS", "댓글 삭제 완료!");
    }

}
