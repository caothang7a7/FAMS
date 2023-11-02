package com.backend.FAMS.dto.User.request;/*  Welcome to Jio word
    @author: Jio
    Date: 10/6/2023
    Time: 4:20 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/


import com.backend.FAMS.entity.User.user_enum.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTOCreateRequest {

    @NotEmpty(message = "Name is required!")
    String name;

    @Email
    @NotEmpty(message = "Email is required!")
    String email;

    @NotEmpty(message = "Phone is required!")
    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$"
            , message = "Invalid phone number!")
    String phone;

    @JsonFormat(timezone = "yyyy-mm-dd")
    @NotNull(message = "Date of birth is required!")
    @Past(message = "Date of birth is greater than the present time")
    Date dob;
    Gender gender;
    boolean status;
    String userPermission;


}
