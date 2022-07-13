package com.project.skb.study.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StudyCreateRequestDto {

    @NotBlank(message = "스터디 이름을 입력해주세요")
    private String title;

    @NotBlank(message = "제한인원을 입력해주세요")
    private int limitedNumber;

}
