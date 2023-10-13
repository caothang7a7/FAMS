package com.backend.FAMS.service.User.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 10/6/2023
    Time: 2:45 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/


import com.backend.FAMS.dto.ApiResponse;
import com.backend.FAMS.dto.User.request.UserDTOUpdateRequest;
import com.backend.FAMS.dto.User.response.UserDTOResponse;
import com.backend.FAMS.entity.User.User;
import com.backend.FAMS.entity.User.UserPermission;
import com.backend.FAMS.entity.User.user_enum.UserRole;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.mapper.User.UserMapper;
import com.backend.FAMS.repository.User.UserPermissionRepository;
import com.backend.FAMS.repository.User.UserRepository;
import com.backend.FAMS.service.User.IUserService;

import com.backend.FAMS.util.User.UserUtil;
import com.backend.FAMS.util.User.ValidatorUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserServiceImpl implements IUserService {

    UserRepository userRepository;
    UserPermissionRepository userPermissionRepository;
    UserMapper userMapper;
    ValidatorUtil validatorUtil;
    UserUtil util;

    @Override
    public List<User> findAll() {

        List<User> users = userRepository.findAll();

        return users;
    }

    public Page<User> findAll(Pageable pageable) {

        Page<User> userPages = userRepository.findAllByStatusTrueOrderByName(pageable);

        return userPages;
    }

    @Override
    public User createUser(User user, String roleName) {
        // Tạo mật khẩu ngẫu nhiên
//        String randomPassword = UserUtil.generateRandomPassword();

        user.setPassword(RandomStringUtils.randomAlphanumeric(10));

        UserRole role = UserRole.valueOf(roleName);

        UserPermission userPermission = userPermissionRepository.findByRole(role).orElseThrow(
                () -> new NotFoundException("Create fail by not found role")
        );

        user.setUserPermission(userPermission);
        System.out.println(userRepository.findById(1L));
        userRepository.save(user);
        user = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new NotFoundException("Create fail by not found user")
        );

        // gui gmail


        return user;
    }

    @Override
    public ResponseEntity updateUser(UserDTOUpdateRequest userDTOUpdateRequest, Long id,
                                     String roleName, BindingResult bindingResult) {
        ApiResponse apiResponse = new ApiResponse();

        util.validate(userDTOUpdateRequest, bindingResult);
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found user.")
        );
        userMapper.toUpdate(userDTOUpdateRequest, user);
//        user.setUserId(id);
        UserRole role = UserRole.valueOf(roleName);
        UserPermission userPermission = userPermissionRepository.findByRole(role).orElseThrow(
                () -> new NotFoundException("Update failed by not found role.")
        );
        if (bindingResult.hasErrors()) {
            apiResponse.error(validatorUtil.handleValidationErrors(bindingResult.getFieldErrors()));
            return ResponseEntity.badRequest().body(apiResponse);
        }
        user.setModifiedDate(new Date());
        user.setUserPermission(userPermission);
        user = userRepository.save(user);
        UserDTOResponse userDTOResponse = userMapper.toResponse(user);
        userDTOResponse.setUserPermission(user.getUserPermission().getRole().toString());
        apiResponse.ok(userDTOResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getUser(Long id) {
        ApiResponse apiResponse = new ApiResponse();
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found user.")
        );
        UserDTOResponse userDTOResponse = userMapper.toResponse(user);
        userDTOResponse.setUserPermission(user.getUserPermission().getRole().toString());
        apiResponse.ok(userDTOResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
