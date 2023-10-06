package com.backend.FAMS.TrainingContent.entity;

import com.backend.FAMS.LearningObjective.entity.LearningObjective;
import com.backend.FAMS.TrainingUnit.entity.TrainingUnit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tblTrainingContent")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingContent {
    @Id
    @Column(name = "training_content_id", nullable = false, unique = true)
    private Long trainingContentId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "note")
    private String note;

    // --- relationship----

    // n-1 to LearningObjective
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "learning_objective_code", nullable = false)
    @JsonIgnore
    private LearningObjective learningObjective;

    // n-1 to TrainingUnit
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_code",nullable = false)
    @JsonIgnore
    private TrainingUnit trainingUnit;

}
