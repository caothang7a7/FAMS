package com.backend.FAMS.entity.Syllabus;


import com.backend.FAMS.entity.Syllabus.syllabus_enum.SyllabusLevel;
import com.backend.FAMS.entity.Syllabus.syllabus_enum.SyllabusStatus;
import com.backend.FAMS.entity.training_program.TrainingProgramSyllabus;
import com.backend.FAMS.entity.training_unit.TrainingUnit;
import com.backend.FAMS.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tblSyllabus")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Syllabus implements Cloneable {
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    @Id
    @Column(name = "topic_code", nullable = true, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String topicCode;

    @Column(name = "topic_name", nullable = true, unique = true)
    private String topicName;

    @Column(name = "technical_group", nullable = true)
    private String technicalGroup;

    @Column(name = "version", nullable = true)
    private String version;

    @Column(name = "training_audience", nullable = true)
    private String trainingAudience;

    @Column(name = "topic_outline", nullable = true)
    private String topicOutline;

    @Column(name = "training_material", nullable = true)
    private String trainingMaterial;

    @Column(name = "training_principal", nullable = true, length = 10000)
    private String trainingPrincipal;

    @Column(name = "priority", nullable = true)
    private String priority;

    @Column(name = "public_status", nullable = true)
    @Enumerated(EnumType.STRING)
    private SyllabusStatus syllabusStatus;

    @Column(name = "created_by", nullable = true)
    private String createdBy;

    @Column(name = "created_date", nullable = true)
    private Date createdDate;

    @Column(name = "modified_by", nullable = true)
    private String modifiedBy;

    @Column(name = "modified_date", nullable = true)
    private Date modifiedDate;

    @Column(name = "quiz", nullable = true)
    private int quiz;

    @Column(name = "assignment", nullable = true)
    private int assignment;

    @Column(name = "final", nullable = true)
    private int finalTest;

    @Column(name = "final_theory", nullable = true)
    private int finalTheory;

    @Column(name = "final_practice", nullable = true)
    private int finalPractice;

    @Column(name = "gpa", nullable = true)
    private int gpa;

    @Column(name = "level", nullable = true)
    @Enumerated(EnumType.STRING)
    private SyllabusLevel level;
    // --- relationship----

    // tao quan he 1-n to SyllabusObjective
    @OneToMany(mappedBy = "syllabus")
    @JsonIgnore
    private Set<SyllabusObjective> syllabusObjectives;

    // 1-n to TrainingUnit
    @OneToMany(mappedBy = "syllabus")
    @JsonIgnore
    private Set<TrainingUnit> trainingUnits;

    //  1-n to TrainingProgramSyllabus
    @OneToMany(mappedBy = "syllabus")
    @JsonIgnore
    private Set<TrainingProgramSyllabus> trainingProgramSyllabusSet;

    // n-1 to User
    @ManyToOne
    @MapsId("userId")
//    @JoinColumn(name = "userId", insertable = false, updatable = false)
    @JoinColumn(nullable = true,insertable = false,name = "userId")
    @JsonIgnore
    private User user;
}
