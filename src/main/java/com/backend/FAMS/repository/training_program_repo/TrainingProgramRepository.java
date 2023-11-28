package com.backend.FAMS.repository.training_program_repo;

import com.backend.FAMS.entity.training_program.TrainingProgram;
import com.backend.FAMS.entity.training_program.trainingprogram_enum.TrainingProgramStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, String> {
    List<TrainingProgram> findByNameContainingIgnoreCase(String name);

    long countByStatus(TrainingProgramStatus status);
}
