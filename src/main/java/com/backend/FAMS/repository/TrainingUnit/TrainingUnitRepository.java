package com.backend.FAMS.repository.TrainingUnit;

import com.backend.FAMS.entity.TrainingContent.TrainingContent;
import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TrainingUnitRepository extends JpaRepository<TrainingUnit, String> {
    Set<TrainingUnit> findBySyllabusTopicCodeOrderByDayNumber(String topicCode);
    Set<TrainingUnit> findBySyllabusTopicCode(String topicCode);
    Set<TrainingUnit> findBySyllabusTopicCodeAndDayNumber(String topicCode, int dayNumber);
    Set<TrainingUnit> findTrainingUnitByDayNumberAndSyllabusTopicCode(int dayNumber,String topicCode);
    Set<TrainingUnit> findTrainingUnitBySyllabusTopicCodeAndUnitCode(String topicCode,String unitCode);
    Set<TrainingUnit> findAllByDayNumber(int dayNumber);
    TrainingUnit findByDayNumberAndSyllabus_TopicCodeAndUnitCode(int dayNumber,String topicCode,String unitCode);
}
