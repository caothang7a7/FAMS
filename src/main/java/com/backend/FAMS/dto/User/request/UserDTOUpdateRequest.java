package com.backend.FAMS.dto.User.request;


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
public class UserDTOUpdateRequest {

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
    String password;
    Date modifiedDate;
    String modifiedBy;

}
