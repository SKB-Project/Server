package com.project.skb.exception.reqeust;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class ExceptionResponseDto {

    private int code;
    private String message;
    private String exceptionContent;

    @Builder
    public ExceptionResponseDto(int code, String message, String exceptionContent){
        this.code = code;
        this.message = message;
        this.exceptionContent = exceptionContent;
    }
}
