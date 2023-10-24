package com.backend.FAMS.dto.security;/*  Welcome to Jio word
    @author: Jio
    Date: 10/17/2023
    Time: 8:20 AM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RefreshTokenRequest {
    private String refreshToken;
}
