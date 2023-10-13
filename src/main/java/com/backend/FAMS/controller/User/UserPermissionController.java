package com.backend.FAMS.controller.User;

import com.backend.FAMS.dto.User.request.UserPermissionDTOUpdateRequest;
import com.backend.FAMS.service.User.IUserPermissionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserPermission(@PathVariable int id,
                                        @Valid @RequestBody UserPermissionDTOUpdateRequest userPermissionDTOUpdateRequest
                                        ) throws ParseException {
        userPermissionService.updateUserPermission(userPermissionDTOUpdateRequest, id);
        return userPermissionService.getAllUserPermission();
    }


}
