package com.project.skb.study.controller;

import com.project.skb.ResponseDto;
import com.project.skb.study.request.StudyCreateRequestDto;
import com.project.skb.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

@RequiredArgsConstructor
@RestController
public class StudyController {

    private final StudyService studyService;

    @PostMapping("/study/create")
    public ResponseDto createStudy(ServletRequest request, @RequestBody StudyCreateRequestDto studyCreateRequestDto){
        return studyService.createStudy(request, studyCreateRequestDto);
    }

    @PostMapping("/study/join/{studyId}")
    public ResponseDto joinStudy(ServletRequest request, @PathVariable Long studyId){
        return studyService.joinStudy(request, studyId);
    }

}
