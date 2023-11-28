package com.backend.FAMS.dto.Syllabus.request;

import com.backend.FAMS.entity.Syllabus.syllabus_enum.SyllabusLevel;
import com.backend.FAMS.entity.learning_objective.learningObjective_enum.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SyllabusDTOCreateGeneralRequest {
    String topicCode;
    @NotEmpty(message = "Syllabus name is required")
    String topicName;
    @NotEmpty(message = "Version is required")
    String version;
    SyllabusLevel level;
    @NotEmpty(message = "Technical Group is required")
    String technicalGroup;
    @NotEmpty(message = "Number of audience is required")
    String trainingAudience;
    @NotEmpty(message = "Description is required")
    String description;
    @JsonFormat(timezone = "yyyy-mm-dd")
    Date createDate;

    @NotNull(message = "UserId is required")
//    @JsonProperty("userId")
//    @Nullable
    Long userID;

    @NotEmpty(message = "Learning Objective name is required")
    String learningObjectiveName;
    String learningObjectiveType;
}
