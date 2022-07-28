package com.project.skb.post.service;

import com.project.skb.ResponseDto;
import com.project.skb.config.security.JwtAuthenticationProvider;
import com.project.skb.exception.domain.PostNotFoundException;
import com.project.skb.post.domain.Post;
import com.project.skb.post.domain.PostRepository;
import com.project.skb.post.request.CreatePostRequestDto;
import com.project.skb.post.request.EditPostRequestDto;
import com.project.skb.post.response.GetPostResponseDto;
import com.project.skb.user.domain.User;
import com.project.skb.user.domain.UserRepository;
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
public class PostService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public ResponseDto createPost(ServletRequest request, CreatePostRequestDto createPostRequestDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Post post = Post.builder()
                .type(createPostRequestDto.getType())
                .title(createPostRequestDto.getTitle())
                .content(createPostRequestDto.getContent())
                .build();

        post.insertUser(user);

        postRepository.save(post);

        return new ResponseDto("SUCCESS", post.getPostId());

    }

    public ResponseDto getPost(ServletRequest request, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException());

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
                .orElseThrow(() -> new PostNotFoundException());

        Post postUpdated = Post.builder()
                .type(editPostRequestDto.getType())
                .title(editPostRequestDto.getTitle())
                .content(editPostRequestDto.getContent())
                .build();

        return new ResponseDto("SUCCESS", postUpdated.getPostId());
    }

    @Transactional
    public ResponseDto deletePost(ServletRequest request, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException());

        postRepository.delete(post);

        return new ResponseDto("SUCCESS", "게시글 삭제 완료!");
    }
}