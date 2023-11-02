package com.backend.FAMS.repository.LearningObjective;

import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface LearningObjectiveRepository extends JpaRepository<LearningObjective, String> {

}
