package com.project.skb.planner.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlanner is a Querydsl query type for Planner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlanner extends EntityPathBase<Planner> {

    private static final long serialVersionUID = -1968865526L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlanner planner = new QPlanner("planner");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Long> plannerId = createNumber("plannerId", Long.class);

    public final StringPath studyTime = createString("studyTime");

    public final StringPath title = createString("title");

    public final com.project.skb.user.domain.QUser user;

    public QPlanner(String variable) {
        this(Planner.class, forVariable(variable), INITS);
    }

    public QPlanner(Path<? extends Planner> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlanner(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlanner(PathMetadata metadata, PathInits inits) {
        this(Planner.class, metadata, inits);
    }

    public QPlanner(Class<? extends Planner> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.project.skb.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

