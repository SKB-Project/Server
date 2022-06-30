package com.project.skb.post.contorller;

import com.project.skb.ResponseDto;
import com.project.skb.post.request.CreatePostRequestDto;
import com.project.skb.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/post/create")
    public ResponseDto createPost(ServletRequest request, @Valid @RequestBody CreatePostRequestDto createPostRequestDto){
        return postService.createPost(request, createPostRequestDto);
    }

    @GetMapping("/post/{postId}/get")
    public ResponseDto getPost(ServletRequest request, @PathVariable Long postId){
        return postService.getPost(request, postId);
    }

}
