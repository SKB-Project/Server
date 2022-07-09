package com.project.skb.study.repository;

import com.project.skb.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study,Long> {
}
