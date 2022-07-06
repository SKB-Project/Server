package com.project.skb.exception.controller;

import com.project.skb.exception.domain.PostNotFoundException;
import com.project.skb.exception.response.ExceptionResponseDto;
import com.sun.nio.sctp.IllegalReceiveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponseDto invalidRequestExceptionHandler(MethodArgumentNotValidException e){
        log.error("[invalidRequestExceptionHandler]: {}",e);

        // "@Valid"에 맞지 않을 때 예외 처리
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .code(400)
                .message("Check Validation")
                .exceptionContent(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .build();
        return exceptionResponseDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionResponseDto requestExceptionHandler(IllegalArgumentException e){
        log.error("[requestExceptionHandler]: {}",e);

        // 잘못된 요청일 때 예외 처리
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .code(400)
                .message("Check Request")
                .exceptionContent(e.getMessage())
                .build();

        return exceptionResponseDto;
    }

    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public ExceptionResponseDto notFoundExceptionHandler(NoHandlerFoundException e){
        log.error("[UrlExceptionHandler]: {}",e);

        // URL 경로가 잘못 되었을 때 예외처리
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .code(404)
                .message("Check URL Path")
                .exceptionContent(e.getMessage())
                .build();

        return exceptionResponseDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionResponseDto methodExceptionHandler(HttpRequestMethodNotSupportedException e){
        log.error("[methodExceptionHandler]: {}",e);

        // "HTTP Method"가 잘못 되었을 때 예외처리
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .code(405)
                .message("Check HTTP Method")
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
    @ExceptionHandler(NullPointerException.class)
    public ExceptionResponseDto nullExceptionHandler(NullPointerException e){
        log.error("[nullExceptionHandler]= {}",e);

        // 널 포인터 예외 처리
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .code(500)
                .message("Occur Null Pointer Exception")
                .exceptionContent(e.getMessage())
                .build();

        return exceptionResponseDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ExceptionResponseDto runtimeExceptionHandler(RuntimeException e){
        log.error("[runtimeExceptionHandler]= {}",e);

        // Runtime Exception 예외 처리
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .code(500)
                .message("Occur Runtime Exception")
                .exceptionContent(e.getMessage())
                .build();

        return exceptionResponseDto;

    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ExceptionResponseDto postNotFoundExceptionHandler(PostNotFoundException e){
        log.error("[PostNotFoundExceptionHandler]= {}",e);

        // Post Not Found 예외 처리
        ExceptionResponseDto exceptionResponseDto = ExceptionResponseDto.builder()
                .code(404)
                .message("Check Post ID")
                .exceptionContent(e.getMessage())
                .build();

        return exceptionResponseDto;
    }


}
