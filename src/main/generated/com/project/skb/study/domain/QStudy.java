package com.project.skb.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy is a Querydsl query type for Study
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudy extends EntityPathBase<Study> {

    private static final long serialVersionUID = -1884080150L;

    public static final QStudy study = new QStudy("study");

    public final NumberPath<Integer> limitedNumber = createNumber("limitedNumber", Integer.class);

    public final NumberPath<Integer> number = createNumber("number", Integer.class);

    public final NumberPath<Long> studyId = createNumber("studyId", Long.class);

    public final StringPath title = createString("title");

    public final ListPath<com.project.skb.user.domain.User, com.project.skb.user.domain.QUser> userList = this.<com.project.skb.user.domain.User, com.project.skb.user.domain.QUser>createList("userList", com.project.skb.user.domain.User.class, com.project.skb.user.domain.QUser.class, PathInits.DIRECT2);

    public QStudy(String variable) {
        super(Study.class, forVariable(variable));
    }

    public QStudy(Path<? extends Study> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudy(PathMetadata metadata) {
        super(Study.class, metadata);
    }

}

