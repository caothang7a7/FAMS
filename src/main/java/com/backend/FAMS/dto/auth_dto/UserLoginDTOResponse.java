package com.backend.FAMS.dto.auth_dto;/*  Welcome to Jio word
    @author: Jio
    Date: 11/21/2023
    Time: 9:36 AM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

import com.backend.FAMS.entity.user.user_enum.Gender;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDTOResponse {
    Long userId;
    String name;
    String email;
    String password;
    String phone;
    String dob;
    Gender gender;
    boolean status;
    Set<GrantedAuthority> authorities = new HashSet<>();
}
