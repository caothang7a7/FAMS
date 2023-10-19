package com.backend.FAMS.dto.Syllabus.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SyllabusDTOCreateGeneralRequest {
    @NotEmpty(message = "Syllabus name is required")
    String topicName;
    @NotEmpty(message = "Syllabus code is required")
    String topicCode;
    @NotEmpty(message = "Version is required")
    String version;
    String level; // chưa có field nào trong db
    @NotEmpty(message = "Technical Group is required")
    String technicalGroup;
    @NotEmpty(message = "Number of audience is required")
    String trainingAudience;
    @NotEmpty(message = "Description is required")
    String description;
}
