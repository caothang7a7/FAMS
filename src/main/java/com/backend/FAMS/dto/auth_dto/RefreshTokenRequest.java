package com.backend.FAMS.dto.auth_dto;/*  Welcome to Jio word
    @author: Jio
    Date: 10/17/2023
    Time: 8:20 AM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RefreshTokenRequest {
    private String refreshToken;
}
