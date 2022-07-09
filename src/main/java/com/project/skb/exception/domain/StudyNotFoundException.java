package com.project.skb.exception.domain;

public class StudyNotFoundException extends RuntimeException{

    private static final String MESSAGE = "존재하지 않는 스터디입니다.";

    public StudyNotFoundException() {
        super(MESSAGE);
    }
}
