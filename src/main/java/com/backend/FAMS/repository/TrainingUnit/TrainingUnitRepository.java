package com.backend.FAMS.repository.TrainingUnit;

import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TrainingUnitRepository extends JpaRepository<TrainingUnit, String> {
    Set<TrainingUnit> findBySyllabusTopicCodeOrderByDayNumber(String topicCode);
}
