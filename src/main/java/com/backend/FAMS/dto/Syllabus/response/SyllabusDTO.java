package com.backend.FAMS.dto.Syllabus.response;


import com.backend.FAMS.entity.Syllabus.syllabus_enum.SyllabusStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SyllabusDTO {
    private String topicCode;
    private String topicName;
    private String technicalGroup;
    private String version;
    private String trainingAudience;
    private String topicOutline;
    private String trainingPrincipal;
    private String priority;
    private SyllabusStatus syllabusStatus;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;

    private int trainingProgramDuration;
    private String learningObjectiveType;
    private String userLevel;
    private String outputStandard;
    private String courseObjective;

// Add more attribute
    private String[][] outputStandardArr;
    private int duration;

    private Integer[][] durationArr;

    private int dayNumber;
}
