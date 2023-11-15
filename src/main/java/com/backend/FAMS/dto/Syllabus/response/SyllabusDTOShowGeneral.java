package com.backend.FAMS.dto.Syllabus.response;

import com.backend.FAMS.entity.Syllabus.SyllabusObjective;
import com.backend.FAMS.entity.Syllabus.syllabus_enum.SyllabusLevel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
public class SyllabusDTOShowGeneral {
    private String topicCode;
    private String topicName;
    private String technicalGroup;
    private String version;
    private String trainingAudience;
    private int quiz;
    private int assignment;
    private int finalTest;
    private int finalTheory;
    private int finalPractice;
    private int gpa;
    private SyllabusLevel level;
    private Set<SyllabusObjective> syllabusObjectives;


}
