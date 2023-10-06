package com.backend.FAMS.TrainingUnit.entity;

import com.backend.FAMS.Syllabus.entity.Syllabus;
import com.backend.FAMS.TrainingContent.entity.TrainingContent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tblTrainingUnit")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingUnit {
    @Id
    @Column(name = "unit_code", nullable = false, unique = true)
    private String unitCode;

    @Column(name = "unit_name", nullable = false)
    private String unitName;

    @Column(name = "day_number")
    private int dayNumber;

    // --- relationship----

    // n-1 to Syllabus
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_code", nullable = false)
    @JsonIgnore
    private Syllabus syllabus;

    // 1-n to TrainingContent
    @OneToMany(mappedBy = "trainingUnit")
    @JsonIgnore
    private Set<TrainingContent> trainingContents;


}
