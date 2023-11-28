package com.backend.FAMS.service.training_program_service;

import com.backend.FAMS.entity.training_program.TrainingProgram;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface TrainingProgramService {
    public TrainingProgram duplicateTrainingProgramById(String trainingProgramCode);

    public void activateTrainingProgramById(String trainingProgramCode);

    public void deactivateTrainingProgramById(String trainingProgramCode);

    public List<TrainingProgram> searchTrainingProgramByName(String name);

    public Resource getFileAsResource(String fileCode) throws IOException;

    public Resource deleteFile(String fileCode) throws IOException;

    public ResponseEntity<byte[]> exportTrainingProgramToExcel(String trainingProgramCode) throws IOException, Exception;
}
