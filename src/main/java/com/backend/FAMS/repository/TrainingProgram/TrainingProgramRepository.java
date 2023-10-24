package com.backend.FAMS.repository.TrainingProgram;

import com.backend.FAMS.entity.TrainingProgram.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, String> {
}
