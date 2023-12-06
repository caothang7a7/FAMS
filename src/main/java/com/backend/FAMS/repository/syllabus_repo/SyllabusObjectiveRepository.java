package com.backend.FAMS.repository.syllabus_repo;

import com.backend.FAMS.entity.Syllabus.SyllabusObjective;
import com.backend.FAMS.entity.learning_objective.LearningObjective;
import com.backend.FAMS.entity.learning_objective.learningObjective_enum.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    List<SyllabusObjective> findSyllabusObjectiveBySyllabus_TopicCode(String topicCode);

    @Transactional
    @Modifying
    @Query("DELETE FROM SyllabusObjective so WHERE so.syllabus.topicCode = :topicCode")
    void deleteSyllabusObjectiveByTopicCode(@Param("topicCode") String topicCode);

}
