package com.backend.FAMS.util.User;/*  Welcome to Jio word
    @author: Jio
    Date: 10/6/2023
    Time: 4:35 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

import com.backend.FAMS.dto.User.request.UserChangePass;
import com.backend.FAMS.dto.User.request.UserDTOCreateRequest;
import com.backend.FAMS.dto.User.request.UserDTOUpdateRequest;
import com.backend.FAMS.entity.User.User;
import com.backend.FAMS.repository.User.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.security.SecureRandom;
import java.util.Random;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserUtil implements Validator {
    UserRepository userRepository;

    static String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    static int PASSWORD_LENGTH = 8;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        UserDTOUpdateRequest userDTOUpdateRequest = (UserDTOUpdateRequest) target;

        if (userRepository.existsByEmail(userDTOUpdateRequest.getEmail())) {
            errors.rejectValue("email", "error.email"
                    , "Email already exists!");
        }
        if (userRepository.existsByPhone(userDTOUpdateRequest.getPhone())) {
            errors.rejectValue("phone", "error.phone"
                    , "Phone already exists!");
        }

//        if (containsConsecutiveChars(userDTOUpdateRequest.getName(), userDTOUpdateRequest.getPassword())) {
//            errors.rejectValue("password", "error.password",
//                    "Password must not be the same as or contain a part of the username.");
//        }

    }

    private static boolean containsConsecutiveChars(String username, String password) {
        String name = username.toUpperCase();
        String passwords = password.toUpperCase();
        for (int i = 0; i < name.length() - 2; i++) {
            String substring = name.substring(i, i + 3);
            if (passwords.contains(substring)) {
                return true;
            }
        }
        return false;

    }
    
    public void validateChangePassword(Object target, Errors errors){
        UserChangePass userChangePass = (UserChangePass) target;

        if(!userChangePass.getNewPassword().equals(userChangePass.getConfirmPassword())){
            errors.rejectValue("password", "error.password", "No match");
        }
    }


    public void validateCreate(Object target, Errors errors) {
        UserDTOCreateRequest userDTOCreateRequest = (UserDTOCreateRequest) target;

        if (userRepository.existsByEmail(userDTOCreateRequest.getEmail())) {
            errors.rejectValue("email", "error.email"
                    , "Email already exists!");
        }
        if (userRepository.existsByPhone(userDTOCreateRequest.getPhone())) {
            errors.rejectValue("phone", "error.phone"
                    , "Phone already exists!");
        }
    }

//    public static String generateRandomPassword() {
//        Random random = new SecureRandom();
//        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
//
//        for (int i = 0; i < PASSWORD_LENGTH; i++) {
//            int randomIndex = random.nextInt(CHARACTERS.length());
//            password.append(CHARACTERS.charAt(randomIndex));
//        }
//
//        return password.toString();
//    }


}
