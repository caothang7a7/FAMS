package com.backend.FAMS.service.user_service;


import com.backend.FAMS.dto.user_request.UserPermissionDTOUpdateRequest;
import com.backend.FAMS.dto.user_response.UserPermissionDTOResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserPermissionService {
    ResponseEntity<UserPermissionDTOResponse> getAllUserPermission();

    //    ResponseEntity<UserPermissionDTOResponse> updateUserPermission(UserPermissionDTOUpdateRequest userPermissionDTOUpdateRequest,
//                                                                   int id);
    ResponseEntity patchUpdateUserPermission(List<UserPermissionDTOUpdateRequest> userPermissionDTOList);
}
