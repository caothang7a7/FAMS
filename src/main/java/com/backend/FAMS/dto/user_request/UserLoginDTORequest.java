package com.backend.FAMS.dto.user_request;/*  Welcome to Jio word
    @author: Jio
    Date: 10/17/2023
    Time: 10:43 AM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTORequest {
    private String email;
    private String password;
}
