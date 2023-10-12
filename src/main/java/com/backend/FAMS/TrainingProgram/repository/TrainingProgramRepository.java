package com.backend.FAMS.TrainingProgram.repository;

import com.backend.FAMS.TrainingProgram.entity.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, String> {
    List<TrainingProgram> findByTrainingProgramCode(String code);
}
