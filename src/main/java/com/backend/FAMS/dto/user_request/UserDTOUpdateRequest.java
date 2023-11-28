package com.backend.FAMS.dto.user_request;


import com.backend.FAMS.entity.user.user_enum.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTOUpdateRequest {

//    @NotEmpty(message = "Name is required!")
    String name;

    @Email
//    @NotEmpty(message = "Email is required!")
    String email;

//    @NotEmpty(message = "Phone is required!")
    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$"
            , message = "Invalid phone number!")
    String phone;

    @JsonFormat(timezone = "yyyy-mm-dd")
//    @NotNull(message = "Date of birth is required!")
    @Past(message = "Date of birth is greater than the present time")
    Date dob;


    Gender gender;
    Boolean status;

    String userPermission;

//    @Pattern.List({
//            @Pattern(regexp = "(?=.[0-9]).+", message = "Password must contain at least one digit."),
//            @Pattern(regexp = "(?=.[a-z]).+", message = "Password must contain at least one lowercase letter."),
//            @Pattern(regexp = "(?=.[A-Z]).+", message = "Password must contain at least one uppercase letter."),
//            @Pattern(regexp = "(?=.[_#?!@$%^&-]).+", message = "Password must contain at least one special character."),
//    })
    String password;
    Date modifiedDate;
    String modifiedBy;





}
