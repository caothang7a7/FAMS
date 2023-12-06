package com.backend.FAMS.entity.Syllabus;

import com.backend.FAMS.entity.learning_objective.LearningObjective;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tblSyllabusObjective")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SyllabusObjective {
    @EmbeddedId
    private com.backend.FAMS.entity.syllabus.SyllabusObjectiveId syllabusObjectiveId;

    // --- relationship----

    // n-1 to syllabus
    @ManyToOne
    @MapsId("topicCode")
    @JoinColumn(name = "topic_code", insertable = true, updatable = true)
    @JsonIgnore
    private Syllabus syllabus;

    // n-1 to learning_objective
    @ManyToOne
    @MapsId("objectiveCode")
    @JoinColumn(name = "objective_code", insertable = true, updatable = true)
    @JsonIgnore
    private LearningObjective learningObjective;
}