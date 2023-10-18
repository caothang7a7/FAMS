package com.backend.FAMS.dto.User.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserChangePass {
    String email;
    String oldPassword;
    String newPassword;
    String confirmPassword;
}
