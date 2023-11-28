package com.backend.FAMS.dto.auth_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginByEmailDTORequest {
    String email;
}
