package com.backend.FAMS.dto.auth_dto;/*  Welcome to Jio word
    @author: Jio
    Date: 9/26/2023
    Time: 11:43 PM
    
    ProjectName: FA_Security
    Jio: I wish you always happy with coding <3
*/

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    private String accessToken;
    //private String refreshToken;
}
