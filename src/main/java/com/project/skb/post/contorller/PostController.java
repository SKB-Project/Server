package com.project.skb.post.contorller;

import com.project.skb.ResponseDto;
import com.project.skb.post.request.CreatePostRequestDto;
import com.project.skb.post.request.EditPostRequestDto;
import com.project.skb.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
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

    @GetMapping("/post/next/{postId}/get")
    public ResponseDto getNextPost(ServletRequest request, @PathVariable Long postId){
        return postService.getNextPost(request, postId);
    }

    @GetMapping("/post/prev/{postId}/get")
    public ResponseDto getPrevPost(ServletRequest request, @PathVariable Long postId){
        return postService.getPrevPost(request, postId);
    }


    @GetMapping("/posts/{type}/get")
    public ResponseDto getPosts(ServletRequest request, @PathVariable String type,
            @PageableDefault(size=50, sort="postId", direction= Sort.Direction.DESC) Pageable pageable) {
        return postService.getPosts(request, type, pageable);
    }

    @PutMapping("/post/{postId}/edit")
    public ResponseDto editPost(ServletRequest request, @PathVariable Long postId,
                                @Validated @RequestBody EditPostRequestDto editPostRequestDto){
        return postService.editPost(request, postId, editPostRequestDto);
    }

    @DeleteMapping("/post/{postId}/delete") // post앞 '/' 추가
    public ResponseDto deletePost(ServletRequest request, @PathVariable Long postId){
        return postService.deletePost(request, postId);
    }

    @PostMapping("/post/{postId}/viewCount") // 게시글 조회수를 올리는 api
    public ResponseDto viewCountPost(ServletRequest request, @PathVariable Long postId){
        return postService.viewCountPost(request, postId);
    }
}
