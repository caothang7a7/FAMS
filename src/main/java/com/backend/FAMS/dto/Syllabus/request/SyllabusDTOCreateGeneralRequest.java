package com.backend.FAMS.dto.Syllabus.request;

import com.backend.FAMS.entity.User.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SyllabusDTOCreateGeneralRequest {
    @NotEmpty(message = "Syllabus name is required")
    String topicName;
    @NotEmpty(message = "Version is required")
    String version;
    String level; // chưa có field nào trong db
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
}
