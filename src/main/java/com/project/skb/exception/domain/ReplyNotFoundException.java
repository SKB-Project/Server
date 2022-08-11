package com.project.skb.exception.domain;

public class ReplyNotFoundException extends RuntimeException{

    private static final String MESSAGE = "존재하지 않는 댓글입니다.";

    public ReplyNotFoundException(){ super(MESSAGE);}
}
