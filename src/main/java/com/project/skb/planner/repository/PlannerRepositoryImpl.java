package com.project.skb.planner.repository;

import com.project.skb.planner.domain.QPlanner;
import com.project.skb.planner.response.GetStudyTimeResponseDto;
import com.project.skb.user.domain.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.time.LocalDate;

@RequiredArgsConstructor
public class PlannerRepositoryImpl implements PlannerRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<GetStudyTimeResponseDto> getStudyTime(Long userId, LocalDate date) {

        List<GetStudyTimeResponseDto> result = jpaQueryFactory
                .select(Projections.fields(GetStudyTimeResponseDto.class,
                        QPlanner.planner.studyTime,
                        QPlanner.planner.title))
                .from(QPlanner.planner)
                .where(userIdEq(userId)
                    .and(QPlanner.planner.date.eq(date)))
                .fetch(); // 조회 대상이 1건, 조회 대상이 여러건일 경우 -> fetch(), "Collection"으로 반환

        return result;
    }

    private BooleanExpression userIdEq(Long userId){
        if(userId == null){
            return null;
        }
        return QUser.user.id.eq(userId);
    }

}
