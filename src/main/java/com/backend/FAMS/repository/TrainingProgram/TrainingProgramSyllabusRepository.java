package com.backend.FAMS.repository.TrainingProgram;


import com.backend.FAMS.TrainingProgram.entity.TrainingProgramSyllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TrainingProgramSyllabusRepository extends JpaRepository<TrainingProgramSyllabus, String> {

    Set<TrainingProgramSyllabus> findAllBySyllabus_TopicCode(String topicCode);

    TrainingProgramSyllabus findFirstBySyllabus_TopicCode(String topicCode);


}
