package com.backend.FAMS.repository.TrainingProgram;

import com.backend.FAMS.entity.TrainingProgram.TrainingProgram;
import com.backend.FAMS.entity.TrainingProgram.TrainingProgramSyllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, String> {
}
