package com.project.skb.planner.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GetStudyTimeRequestDto {
    private Long userId;
    private LocalDate date;
}
