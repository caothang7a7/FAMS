package com.backend.FAMS.service.User.impl;


import com.backend.FAMS.dto.ApiResponse;
import com.backend.FAMS.dto.User.request.UserPermissionDTOUpdateRequest;
import com.backend.FAMS.dto.User.response.UserPermissionDTOResponse;
import com.backend.FAMS.entity.User.UserPermission;
import com.backend.FAMS.exception.ApplicationException;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.mapper.User.UserPermissionMapper;
import com.backend.FAMS.repository.User.UserPermissionRepository;
import com.backend.FAMS.service.User.IUserPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserPermissionServiceImpl implements IUserPermissionService {
    UserPermissionRepository userPermissionRepository;
    UserPermissionMapper userPermissionMapper;

    @Override
    public ResponseEntity getAllUserPermission() {

        try {
            ApiResponse apiResponse = new ApiResponse();
            List<UserPermission> userPermissions = userPermissionRepository.findAll();

            apiResponse.ok(userPermissions);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public ResponseEntity updateUserPermission(UserPermissionDTOUpdateRequest userPermissionDTOUpdateRequest,
                                               int id) {
        ApiResponse apiResponse = new ApiResponse();
        UserPermission userPermission = userPermissionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found user.")
        );

        userPermissionMapper.toUpdate(userPermissionDTOUpdateRequest, userPermission);
        userPermissionRepository.save(userPermission);

        UserPermissionDTOResponse userPermissionDTOResponse = userPermissionMapper.toResponse(userPermission);
        userPermissionDTOResponse.setRole(userPermission.getRole().toString());
        apiResponse.ok(userPermissionDTOResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
