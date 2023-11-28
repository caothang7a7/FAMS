package com.backend.FAMS.repository.learning_objective_repo;

import com.backend.FAMS.entity.learning_objective.LearningObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningObjectiveRepository extends JpaRepository<LearningObjective, String> {
    LearningObjective findByObjectiveCode(String topicCode);
}
