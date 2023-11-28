package com.backend.FAMS.dto.user_response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPermissionDTOResponse {

    private String role;
    private String syllabus;
    private String trainingProgram;
    private String closs;
    private String learningMaterial;
    private String userManagement;


}
