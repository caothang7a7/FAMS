package com.backend.FAMS.dto.Syllabus.response;

import com.backend.FAMS.entity.Syllabus.syllabus_enum.SyllabusStatus;
import com.backend.FAMS.entity.learning_objective.learningObjective_enum.Type;
import lombok.Data;

import java.util.Date;
@Data
public class SyllabusDTODetailInformation {
    private String topicName;
    private SyllabusStatus syllabusStatus;
    private String topicCode;
    private String version;
    private Date modifiedDate;
    private String modifiedBy;
    private String userLevel;
    private String trainingAudience;
    private String technicalGroup;
    private Type courseObjective;
    private String outputStandard;
    private Integer[][] durationArr;
    private String[][] OutputStandardArr;
}
