package com.project.skb.study.controller;

import com.project.skb.ResponseDto;
import com.project.skb.study.request.StudyCreateRequestDto;
import com.project.skb.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class StudyController {

    private final StudyService studyService;

    @PostMapping("/study/create")
    public ResponseDto createStudy(ServletRequest request,
                                   @Valid @RequestBody StudyCreateRequestDto studyCreateRequestDto){
        return studyService.createStudy(request, studyCreateRequestDto);
    }

    @PostMapping("/study/join/{studyId}")
    public ResponseDto joinStudy(ServletRequest request, @PathVariable Long studyId){
        return studyService.joinStudy(request, studyId);
    }

    @DeleteMapping("study/banish/{userId}")
    public ResponseDto banishUser(ServletRequest request, @PathVariable Long userId){
        return studyService.banishUser(request, userId);
    }

}
