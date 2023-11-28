package com.backend.FAMS.controller.user_controller;/*  Welcome to Jio word
    @author: Jio
    Date: 10/6/2023
    Time: 3:03 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/


import com.backend.FAMS.dto.api_response.ApiResponse;
import com.backend.FAMS.dto.user_request.UserChangePass;
import com.backend.FAMS.dto.user_request.UserDTOCreateRequest;
import com.backend.FAMS.dto.user_request.UserDTOUpdateRequest;
import com.backend.FAMS.dto.user_response.UserDTOResponse;
import com.backend.FAMS.entity.user.User;
import com.backend.FAMS.exception.ApplicationException;
import com.backend.FAMS.mapper.user_mapper.UserMapper;
import com.backend.FAMS.service.jwt_service.IJwtService;
import com.backend.FAMS.service.refreshtoken_service.RefreshTokenService;
import com.backend.FAMS.service.user_service.IUserService;
import com.backend.FAMS.util.UserUtil;
import com.backend.FAMS.util.ValidatorUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;
    private final ValidatorUtil validatorUtil;
    private final UserUtil util;
    private final IJwtService jwtService;
    private final RefreshTokenService refreshTokenService;


  //  @PreAuthorize("hasAnyAuthority('VIEW_USER MANAGEMENT')")
//    @GetMapping
//    public ResponseEntity<?> getUsers(@RequestParam(defaultValue = "1") int page) {
////        try {
//            ApiResponse apiResponse = new ApiResponse();
//            PageRequest pageRequest = PageRequest.of(page - 1, 9);
//
//            Page<user_controller> userPage = userService.findAll(pageRequest);
//
//            Page<UserDTOResponse> userDTOPage = userPage.map(user_controller -> {
//            UserDTOResponse userDTOResponse = userMapper.toResponse(user_controller);
//            userDTOResponse.setUserPermission(user_controller.getUserPermission().getRole().toString());
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            userDTOResponse.setDob(dateFormat.format(user_controller.getDob()));
//            return userDTOResponse;
//          });
//
//            apiResponse.ok(userDTOPage);
//            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
////        } catch (Exception ex) {
////            System.out.println("biibi");
////            throw new ApplicationException();
////        }
//    }
    @PostAuthorize("hasAnyAuthority('VIEW_USER MANAGEMENT')")
    @GetMapping
    public ResponseEntity<?> getUsers() {
        try {
        ApiResponse apiResponse = new ApiResponse();

        List<User> userPage = userService.findAll();
        List<UserDTOResponse> userDTOPage = userPage.stream().map(user -> {
            UserDTOResponse userDTOResponse = userMapper.toResponse(user);
            userDTOResponse.setUserPermission(user.getUserPermission().getRole().toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            userDTOResponse.setDob(dateFormat.format(user.getDob()));
            return userDTOResponse;
        }).collect(Collectors.toList());
        apiResponse.ok(userDTOPage);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println("biibi");
            throw new ApplicationException();
        }
    }
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','CLASS_ADMIN')")
    @PostAuthorize("hasAnyAuthority('CREATE_USER MANAGEMENT')")
    @PostMapping
    public ResponseEntity<?> postUser(@Valid @RequestBody UserDTOCreateRequest userDTOCreateRequest,
                                      BindingResult bindingResult,
                                      Authentication authentication) throws ParseException {
        return userService.createUser(authentication,userDTOCreateRequest,bindingResult);

    }
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    @PostAuthorize("hasAnyAuthority('MODIFYS_USER MANAGEMENT')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, Authentication authentication,
                                         @Valid @RequestBody UserDTOUpdateRequest userDTOUpdateRequest,
                                        BindingResult bindingResult) throws ParseException {

        return userService.updateUser(authentication, userDTOUpdateRequest, id, bindingResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUser(@PathVariable Long id) {
        return userService.getUser(id);
    }


    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody UserChangePass userChangePass,
                                            BindingResult bindingResult) {

        return userService.changePassword(userChangePass, bindingResult);


    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(@RequestParam("keyword") List<String> keywords) {

        ApiResponse apiResponse = new ApiResponse();
        List<UserDTOResponse> userDTOResponses =userService.searchUsers(keywords);
        apiResponse.ok(userDTOResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
