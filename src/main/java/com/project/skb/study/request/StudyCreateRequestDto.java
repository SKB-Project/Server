package com.project.skb.study.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudyCreateRequestDto {

    @NotBlank(message = "스터디 이름을 입력해주세요")
    private String title;

    @NotNull(message = "제한인원을 입력해주세요") // "@NotBlank"는 문자나 문자열에만 적용
    private Integer limitedNumber;

}
