package com.project.skb.post.service;

import com.project.skb.ResponseDto;
import com.project.skb.config.JwtAuthenticationProvider;
import com.project.skb.post.domain.Post;
import com.project.skb.post.domain.PostRepository;
import com.project.skb.post.request.CreatePostRequestDto;
import com.project.skb.user.domain.User;
import com.project.skb.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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

        Post post = Post.builder().
                type(createPostRequestDto.getType())
                .title(createPostRequestDto.getTitle())
                .content(createPostRequestDto.getContent())
                .build();

        post.setUser(user);

        postRepository.save(post);

        return new ResponseDto("SUCCESS", post.getPostId());

    }
}