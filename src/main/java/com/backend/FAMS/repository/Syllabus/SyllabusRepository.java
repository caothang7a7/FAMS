package com.backend.FAMS.repository.Syllabus;

import com.backend.FAMS.entity.Syllabus.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, String> {

    Syllabus findSyllabusByTopicName(String topicName);
}
