package com.project.skb.planner.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class WriteStudyTimeRequestDto {

    @NotBlank(message = "오늘 공부한 유형을 입력해주세요.")
    private String title;
    @NotBlank(message = "공부 시간을 입력해주세요.")
    private String studyTime;

}
