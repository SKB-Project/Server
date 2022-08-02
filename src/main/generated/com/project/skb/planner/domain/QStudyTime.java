package com.project.skb.planner.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyTime is a Querydsl query type for StudyTime
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QStudyTime extends BeanPath<StudyTime> {

    private static final long serialVersionUID = -2070415506L;

    public static final QStudyTime studyTime = new QStudyTime("studyTime");

    public final TimePath<java.time.LocalTime> endTime = createTime("endTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> startTime = createTime("startTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> totalStudyTime = createTime("totalStudyTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> wakeUpTime = createTime("wakeUpTime", java.time.LocalTime.class);

    public QStudyTime(String variable) {
        super(StudyTime.class, forVariable(variable));
    }

    public QStudyTime(Path<? extends StudyTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyTime(PathMetadata metadata) {
        super(StudyTime.class, metadata);
    }

}

