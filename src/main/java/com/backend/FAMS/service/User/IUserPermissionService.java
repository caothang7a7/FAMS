package com.backend.FAMS.service.User;


import com.backend.FAMS.dto.User.request.UserPermissionDTOUpdateRequest;
import com.backend.FAMS.dto.User.response.UserPermissionDTOResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IUserPermissionService {
    ResponseEntity<UserPermissionDTOResponse> getAllUserPermission();
    ResponseEntity<UserPermissionDTOResponse> updateUserPermission(UserPermissionDTOUpdateRequest userPermissionDTOUpdateRequest,
                                                                   int id);

}
