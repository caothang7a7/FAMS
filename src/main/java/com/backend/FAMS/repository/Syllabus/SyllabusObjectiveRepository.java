package com.backend.FAMS.repository.Syllabus;

import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import com.backend.FAMS.entity.Syllabus.SyllabusObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyllabusObjectiveRepository extends JpaRepository<SyllabusObjective, String> {
    LearningObjective findBySyllabus_TopicCode(String topicCode);
}
