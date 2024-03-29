package com.backend.FAMS.entity.training_program;

import com.backend.FAMS.entity.Syllabus.Syllabus;
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

//    @Column(name = "sequence")
//    private int sequence;


    // --- relationship----

    // n-1 to file_upload_dto
    @ManyToOne
    @MapsId("trainingProgramCode")
    @JoinColumn(name = "training_program_code", insertable = false, updatable = false)
    @JsonIgnore
    private TrainingProgram trainingProgram;

    // n-1 to syllabus
    @ManyToOne
    @MapsId("topicCode")
    @JoinColumn(name = "topic_code", insertable = false, updatable = false)
    @JsonIgnore
    private Syllabus syllabus;
}