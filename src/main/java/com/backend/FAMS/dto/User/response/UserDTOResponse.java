package com.backend.FAMS.dto.User.response;/*  Welcome to Jio word
    @author: Jio
    Date: 10/6/2023
    Time: 2:51 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/


import com.backend.FAMS.entity.User.user_enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTOResponse{

    String name;
    String email;
    String phone;
    Date dob;
    Gender gender;
    boolean status;
    String userPermission;


}
