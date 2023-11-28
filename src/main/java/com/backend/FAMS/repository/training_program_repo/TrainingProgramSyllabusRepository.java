package com.backend.FAMS.repository.training_program_repo;

import com.backend.FAMS.entity.training_program.TrainingProgramSyllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TrainingProgramSyllabusRepository extends JpaRepository<TrainingProgramSyllabus, String> {
    Set<TrainingProgramSyllabus> findAllBySyllabus_TopicCode(String topicCode);
}
