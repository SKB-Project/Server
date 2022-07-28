package com.project.skb.planner.domain;

import com.project.skb.planner.domain.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannerRepository extends JpaRepository<Planner,Long> {
}
