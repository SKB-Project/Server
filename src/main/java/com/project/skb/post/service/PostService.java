package com.project.skb.post.service;

import com.project.skb.ResponseDto;
import com.project.skb.config.security.JwtAuthenticationProvider;
import com.project.skb.post.domain.Post;
import com.project.skb.post.repository.PostRepository;
import com.project.skb.post.request.CreatePostRequestDto;
import com.project.skb.post.request.EditPostRequestDto;
import com.project.skb.post.response.GetPostResponseDto;
import com.project.skb.user.domain.User;
import com.project.skb.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public ResponseDto createPost(ServletRequest request, CreatePostRequestDto createPostRequestDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Post post = Post.builder()
                .type(createPostRequestDto.getType())
                .title(createPostRequestDto.getTitle())
                .content(createPostRequestDto.getContent())
                .build();

        post.setUser(user);

        postRepository.save(post);

        return new ResponseDto("SUCCESS", post.getPostId());

    }

    public ResponseDto getPost(ServletRequest request, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        GetPostResponseDto getPostResponseDto = GetPostResponseDto.builder()
                .type(post.getType())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        return new ResponseDto("SUCCESS", getPostResponseDto);
    }

    public ResponseDto getPosts(ServletRequest request, String type, Pageable pageable) {
        List<GetPostResponseDto> pages = postRepository.findByType(type, pageable)
                .stream()
                .map(post -> new GetPostResponseDto(post))
                .collect(Collectors.toList());

        return new ResponseDto("SUCCESS",pages);

    }

    @Transactional
    public ResponseDto editPost(ServletRequest request, Long postId, EditPostRequestDto editPostRequestDto) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        if (!post.getType().equals(editPostRequestDto.getType())) { // type 변경
            post.setType(editPostRequestDto.getType());
            postRepository.save(post);
        }

        if (!post.getTitle().equals(editPostRequestDto.getTitle())) { // title 변경
            post.setTitle(editPostRequestDto.getTitle());
            postRepository.save(post);
        }

        if (!post.getContent().equals(editPostRequestDto.getContent())) { // content 변경
            post.setContent(editPostRequestDto.getContent());
            postRepository.save(post);
        }

        return new ResponseDto("SUCCESS", post.getPostId());
    }

    public ResponseDto deletePost(ServletRequest request, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        postRepository.delete(post);

        return new ResponseDto("SUCCESS", "게시글 삭제 완료!");
    }
}