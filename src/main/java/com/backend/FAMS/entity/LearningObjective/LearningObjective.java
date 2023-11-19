package com.backend.FAMS.entity.LearningObjective;

import com.backend.FAMS.entity.LearningObjective.learningObjective_enum.Type;
import com.backend.FAMS.entity.Syllabus.SyllabusObjective;
import com.backend.FAMS.entity.TrainingContent.TrainingContent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tblLearningObjective")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearningObjective {
    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String objectiveCode;

    @Column(name = "name", nullable = true)
    private String objectiveName;

    @Column(name = "type", nullable = true)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "description", nullable = false)
    private String description;

    // --- relationship----

    // 1-n to SyllabusObjective
    @OneToMany(mappedBy = "learningObjective")
    @JsonIgnore
    private Set<SyllabusObjective> syllabusObjectives;

    // 1-n to TrainingContent
    @OneToMany(mappedBy = "learningObjective")
    @JsonIgnore
    private Set<TrainingContent> trainingContent;
}
