package com.backend.FAMS.TrainingProgram.repository;

import com.backend.FAMS.TrainingProgram.entity.TrainingProgramSyllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingProgramSyllabusRepository extends JpaRepository<TrainingProgramSyllabus, String> {
}
