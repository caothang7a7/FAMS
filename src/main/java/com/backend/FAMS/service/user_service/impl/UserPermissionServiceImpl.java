package com.backend.FAMS.service.user_service.impl;


import com.backend.FAMS.dto.api_response.ApiResponse;
import com.backend.FAMS.dto.user_request.UserPermissionDTOUpdateRequest;
import com.backend.FAMS.entity.user.UserPermission;
import com.backend.FAMS.entity.user.user_enum.UserPermissionStatus;
import com.backend.FAMS.entity.user.user_enum.UserRole;
import com.backend.FAMS.exception.ApplicationException;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.mapper.userpermission_mapper.UserPermissionMapper;
import com.backend.FAMS.repository.user_repo.UserPermissionRepository;
import com.backend.FAMS.service.user_service.IUserPermissionService;
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

    //    @Override
//    public ResponseEntity updateUserPermission(UserPermissionDTOUpdateRequest userPermissionDTOUpdateRequest,
//                                               int id) {
//        ApiResponse apiResponse = new ApiResponse();
//        UserPermission userPermission = userPermissionRepository.findById(id).orElseThrow(
//                () -> new NotFoundException("Not found user_controller.")
//        );
//
//        userPermissionMapper.toUpdate(userPermissionDTOUpdateRequest, userPermission);
//        userPermissionRepository.save(userPermission);
//
//        UserPermissionDTOResponse userPermissionDTOResponse = userPermissionMapper.toResponse(userPermission);
//        userPermissionDTOResponse.setRole(userPermission.getRole().toString());
//        apiResponse.ok(userPermissionDTOResponse);
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }
    @Override
    public ResponseEntity patchUpdateUserPermission(List<UserPermissionDTOUpdateRequest> userPermissionDTOList) {
        ApiResponse apiResponse = new ApiResponse();

        for (UserPermissionDTOUpdateRequest userPermissionDTO : userPermissionDTOList) {
            if (userPermissionDTO.getRole() == null) {
                // Nếu role không được chỉ định, bỏ qua và không thực hiện cập nhật
                continue;
            }

            UserPermission userPermission = userPermissionRepository.findByRole(UserRole.valueOf(userPermissionDTO.getRole())).orElseThrow(() -> new NotFoundException("Not found user_controller with role: " + userPermissionDTO.getRole()));

            if(userPermissionDTO.getSyllabus()!=null){
                userPermission.setSyllabus(UserPermissionStatus.valueOf(userPermissionDTO.getSyllabus()));
            }
            if(userPermissionDTO.getTrainingProgram()!=null){
                userPermission.setTrainingProgram(UserPermissionStatus.valueOf(userPermissionDTO.getTrainingProgram()));
            }
            if(userPermissionDTO.getCloss()!=null){
                userPermission.setCloss(UserPermissionStatus.valueOf(userPermissionDTO.getCloss()));
            }
            if(userPermissionDTO.getLearningMaterial()!=null){
                userPermission.setLearningMaterial(UserPermissionStatus.valueOf(userPermissionDTO.getLearningMaterial()));
            }

            userPermissionRepository.save(userPermission);
        }

        apiResponse.ok("user_controller permissions updated successfully.");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
