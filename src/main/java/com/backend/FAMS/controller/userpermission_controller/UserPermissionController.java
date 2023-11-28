package com.backend.FAMS.controller.userpermission_controller;

import com.backend.FAMS.dto.user_request.UserPermissionDTOUpdateRequest;
import com.backend.FAMS.service.user_service.IUserPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/usersPermission")
public class UserPermissionController {
    IUserPermissionService userPermissionService;

    @GetMapping()
    public ResponseEntity<?> getUserPermissions() {
        return userPermissionService.getAllUserPermission();
    }

    //    @PutMapping("/{id}")
//    public ResponseEntity<?> updateUserPermission(@PathVariable int id,
//                                        @Valid @RequestBody UserPermissionDTOUpdateRequest userPermissionDTOUpdateRequest
//                                        ) throws ParseException {
//        userPermissionService.updateUserPermission(userPermissionDTOUpdateRequest, id);
//        return userPermissionService.getAllUserPermission();
//    }
    @PatchMapping
    public ResponseEntity<?> patchUpdateUserPermissions(@RequestBody List<UserPermissionDTOUpdateRequest> userPermissionDTOList) {
        userPermissionService.patchUpdateUserPermission(userPermissionDTOList);
        return userPermissionService.getAllUserPermission();
    }

}
