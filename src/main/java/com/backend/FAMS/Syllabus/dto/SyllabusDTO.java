package com.backend.FAMS.Syllabus.dto;

import com.backend.FAMS.Syllabus.syllabus_enum.SyllabusStatus;
import com.backend.FAMS.TrainingContent.entity.TrainingContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Optional;

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
// Add more attribute
    private String[][] outputStandard;
    private int duration;
}
