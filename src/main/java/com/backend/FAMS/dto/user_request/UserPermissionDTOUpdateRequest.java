package com.backend.FAMS.dto.user_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionDTOUpdateRequest {
    private String role;
    private String syllabus;
    private String trainingProgram;
    private String closs;
    private String learningMaterial;
    private String userManagement;
}
