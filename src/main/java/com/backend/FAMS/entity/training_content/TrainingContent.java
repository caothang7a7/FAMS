package com.backend.FAMS.entity.training_content;

import com.backend.FAMS.entity.learning_objective.LearningObjective;
import com.backend.FAMS.entity.training_content.trainingContent_enum.DeliveryType;
import com.backend.FAMS.entity.training_content.trainingContent_enum.TrainingFormat;
import com.backend.FAMS.entity.training_unit.TrainingUnit;
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

    @Column(name = "trainingformat")
    @Enumerated(EnumType.STRING)
    private TrainingFormat trainingFormat;

    @Column(name = "deliverytype")
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    // --- relationship----

    // n-1 to learning_objective
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "learning_objective_code", nullable = false)
    @JsonIgnore
    private LearningObjective learningObjective;

    // n-1 to training_unit
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_code",nullable = false)
    @JsonIgnore
    private TrainingUnit trainingUnit;

}
