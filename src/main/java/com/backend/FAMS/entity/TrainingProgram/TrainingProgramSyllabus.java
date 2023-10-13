package com.backend.FAMS.entity.TrainingProgram;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tblTrainingProgramSyllabus")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingProgramSyllabus {
    @EmbeddedId
    private TrainingProgramSyllabusId trainingProgramSyllabusId;

    @Column(name = "sequence")
    private int sequence;

    // --- relationship----

    // n-1 to TrainingProgram
    @ManyToOne
    @MapsId("trainingProgramCode")
    @JoinColumn(name = "training_program_code", insertable = false, updatable = false)
    @JsonIgnore
    private TrainingProgram trainingProgram;

    // n-1 to Syllabus
    @ManyToOne
    @MapsId("topicCode")
    @JoinColumn(name = "topic_code", insertable = false, updatable = false)
    @JsonIgnore
    private Syllabus syllabus;
}