package com.project.skb.planner.controller;

import com.project.skb.ResponseDto;
import com.project.skb.planner.request.GetStudyTimeRequestDto;
import com.project.skb.planner.request.WriteStudyTimeRequestDto;
import com.project.skb.planner.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class PlannerController {

    private final PlannerService plannerService;

    @PostMapping("study/planner/time/create")
    public ResponseDto writeStudyContent(ServletRequest request ,
                                      @Valid @RequestBody WriteStudyTimeRequestDto writeStudyTimeRequestDto){
        return plannerService.writeStudyContent(request, writeStudyTimeRequestDto);
    }

    @GetMapping("study/planner/studyTime/day/get")
    public ResponseDto getStudyTime(@RequestBody GetStudyTimeRequestDto getStudyTimeRequestDto){
        return plannerService.getStudyTimeDay(getStudyTimeRequestDto);
    }

    @GetMapping("study/planner/studyTime/month/get")
    public ResponseDto getStudyTimeMonth(@RequestBody GetStudyTimeRequestDto getStudyTimeRequestDto){
        return plannerService.getStudyTimeMonth(getStudyTimeRequestDto);
    }
}
