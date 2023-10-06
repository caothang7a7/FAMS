package com.backend.FAMS.LearningObjective.repository;

import com.backend.FAMS.LearningObjective.entity.LearningObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningObjectiveRepository extends JpaRepository<LearningObjective, String> {
}
