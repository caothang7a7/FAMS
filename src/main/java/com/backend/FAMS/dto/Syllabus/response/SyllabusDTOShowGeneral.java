package com.backend.FAMS.dto.Syllabus.response;

import com.backend.FAMS.entity.Syllabus.SyllabusObjective;
import com.backend.FAMS.entity.Syllabus.syllabus_enum.SyllabusLevel;
import com.backend.FAMS.entity.Syllabus.syllabus_enum.SyllabusStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
public class SyllabusDTOShowGeneral {
    private String topicCode;
    private String topicName;
    private String technicalGroup;
    private String version;
    private String trainingAudience;
    private SyllabusStatus syllabusStatus;
    private SyllabusLevel level;
    String[][] outputStandardArr;
    int duration;
    private Date modifiedDate;
    private String modifiedBy;
    private Set<SyllabusObjective> syllabusObjectives;


}
