package com.backend.FAMS.dto.Syllabus.response;


import com.backend.FAMS.entity.Syllabus.syllabus_enum.SyllabusStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SyllabusDTOResponse {
     String topicCode;
     String topicName;
     String technicalGroup;
     String version;
     String trainingAudience;
     String topicOutline;
     String trainingPrincipal;
     String priority;
     SyllabusStatus syllabusStatus;
     String createdBy;
     Date createdDate;
     String modifiedBy;
     Date modifiedDate;

     int trainingProgramDuration;
     String learningObjectiveType;
     String userLevel;
     String outputStandard;
     String courseObjective;

// Add more attribute
     String[][] outputStandardArr;
     int duration;

     Integer[][] durationArr;

     int dayNumber;
}
