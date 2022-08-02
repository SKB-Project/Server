package com.project.skb.planner.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTodayStudy is a Querydsl query type for TodayStudy
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QTodayStudy extends BeanPath<TodayStudy> {

    private static final long serialVersionUID = -1733650736L;

    public static final QTodayStudy todayStudy = new QTodayStudy("todayStudy");

    public final TimePath<java.time.LocalTime> studyTime = createTime("studyTime", java.time.LocalTime.class);

    public final StringPath title = createString("title");

    public QTodayStudy(String variable) {
        super(TodayStudy.class, forVariable(variable));
    }

    public QTodayStudy(Path<? extends TodayStudy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTodayStudy(PathMetadata metadata) {
        super(TodayStudy.class, metadata);
    }

}

