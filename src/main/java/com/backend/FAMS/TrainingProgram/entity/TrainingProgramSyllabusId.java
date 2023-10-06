package com.backend.FAMS.TrainingProgram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class TrainingProgramSyllabusId implements Serializable {
    @Column(name = "training_program_code")
    private String trainingProgramCode;

    @Column(name = "topic_code")
    private String topicCode;
}