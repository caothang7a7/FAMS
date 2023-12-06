package com.backend.FAMS.repository.training_unit_repo;

import com.backend.FAMS.entity.training_unit.TrainingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TrainingUnitRepository extends JpaRepository<TrainingUnit, String> {
    Set<TrainingUnit> findBySyllabusTopicCodeOrderByDayNumber(String topicCode);
    Set<TrainingUnit> findBySyllabusTopicCode(String topicCode);
    Set<TrainingUnit> findBySyllabusTopicCodeAndDayNumber(String topicCode, int dayNumber);
    Set<TrainingUnit> findTrainingUnitByDayNumberAndSyllabusTopicCodeOrderByUnitCodeAsc(int dayNumber,String topicCode);
    Set<TrainingUnit> findBySyllabus_TopicCodeAndUnitCode(String topicCode,String unitCode);
    Set<TrainingUnit> findAllByDayNumber(int dayNumber);
    TrainingUnit findTrainingUnitBySyllabusTopicCodeAndUnitCode(String topicCode,String unitCode);
    TrainingUnit deleteByUnitCode(String unitCode);
}
