package com.project.skb.planner.controller;

import com.project.skb.ResponseDto;
import com.project.skb.planner.request.WriteStudyTimeRequestDto;
import com.project.skb.planner.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

@RequiredArgsConstructor
@RestController
public class PlannerController {

    private final PlannerService plannerService;

    @PostMapping("study/planner/time/create")
    public ResponseDto writeStudyTime(ServletRequest request ,@RequestBody WriteStudyTimeRequestDto writeStudyTimeRequestDto){
        return plannerService.writeStudyTime(request, writeStudyTimeRequestDto);
    }

}
