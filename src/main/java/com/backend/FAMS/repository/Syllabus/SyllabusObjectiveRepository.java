package com.backend.FAMS.repository.Syllabus;

import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import com.backend.FAMS.entity.LearningObjective.learningObjective_enum.Type;
import com.backend.FAMS.entity.Syllabus.SyllabusObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusObjectiveRepository extends JpaRepository<SyllabusObjective, String> {
    @Query("SELECT lo FROM LearningObjective lo " +
            "JOIN lo.syllabusObjectives so " +
            "JOIN so.syllabus s " +
            "WHERE s.topicCode = :topicCode AND lo.type = :type")
    LearningObjective findBySyllabusTopicCodeAndLearningObjectiveType(
            @Param("topicCode") String topicCode,
            @Param("type") Type type);
    @Query("SELECT lo FROM LearningObjective lo " +
            "JOIN lo.syllabusObjectives so " +
            "JOIN so.syllabus s " +
            "WHERE s.topicCode = :topicCode")
    LearningObjective findLearningObjectivebySyllabusTopicCode(
            @Param("topicCode") String topicCode);
}
