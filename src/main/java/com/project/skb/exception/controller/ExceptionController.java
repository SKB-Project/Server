package com.project.skb.exception.controller;

import com.project.skb.exception.response.ExceptionResponseDto;
import com.sun.nio.sctp.IllegalReceiveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponseDto invalidRequestExceptionHandler(MethodArgumentNotValidException e){
        log.error("[invalidRequestExceptionHadnler]: {}",e);

        // "@Valid"에 맞지 않을 때 예외 처리
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .code(400)
                .message("Bad Request")
                .exceptionContent(e.getMessage())
                .build();
        return exceptionResponseDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionResponseDto requestExceptionHandler(IllegalReceiveException e){
        log.error("[requestExceptionHandler]: {}",e);

        // 잘못된 요청일 때 예외 처리
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .code(400)
                .message("Bad Request")
                .exceptionContent(e.getMessage())
                .build();

        return exceptionResponseDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ExceptionResponseDto exceptionHandler(Exception e){
        log.error("[exceptionHandler]= {}",e);

        // 서버 예외 처리
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .code(500)
                .message("Server Error")
                .exceptionContent(e.getMessage())
                .build();

        return exceptionResponseDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ExceptionResponseDto runtimeExceptionHandler(RuntimeException e){
        log.error("[runtimeExceptionHandler]= {}",e);

        // 서버 런타임 예외 처리
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .code(500)
                .message("Server Error")
                .exceptionContent(e.getMessage())
                .build();

        return exceptionResponseDto;

    }

    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public ExceptionResponseDto nullExceptionHandler(NullPointerException e){
        log.error("[nullExceptionHandler]= {}",e);

        // 널 포인터 예외 처리
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .code(500)
                .message("Server Error")
                .exceptionContent(e.getMessage())
                .build();

        return exceptionResponseDto;
    }
}
