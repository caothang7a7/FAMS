package com.backend.FAMS.entity.Syllabus;

import com.backend.FAMS.entity.LearningObjective.LearningObjective;
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
    private SyllabusObjectiveId syllabusObjectiveId;

    // --- relationship----

    // n-1 to Syllabus
    @ManyToOne
    @MapsId("topicCode")
    @JoinColumn(name = "topic_code", insertable = false, updatable = false)
    @JsonIgnore
    private Syllabus syllabus;

    // n-1 to LearningObjective
    @ManyToOne
    @MapsId("objectiveCode")
    @JoinColumn(name = "objective_code", insertable = false, updatable = false)
    @JsonIgnore
    private LearningObjective learningObjective;
}