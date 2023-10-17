package com.backend.FAMS.controller.User;/*  Welcome to Jio word
    @author: Jio
    Date: 10/6/2023
    Time: 3:03 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/


import com.backend.FAMS.dto.ApiResponse;
import com.backend.FAMS.dto.User.request.UserChangePass;
import com.backend.FAMS.dto.User.request.UserDTOCreateRequest;
import com.backend.FAMS.dto.User.request.UserDTOUpdateRequest;
import com.backend.FAMS.dto.User.response.UserDTOResponse;
import com.backend.FAMS.entity.Security.RefreshToken;
import com.backend.FAMS.entity.User.User;
import com.backend.FAMS.exception.ApplicationException;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.mapper.User.UserMapper;
import com.backend.FAMS.repository.User.UserRepository;
import com.backend.FAMS.service.User.IUserService;
import com.backend.FAMS.service.sercutity.IJwtService;
import com.backend.FAMS.service.sercutity.RefreshTokenService;
import com.backend.FAMS.util.User.UserUtil;
import com.backend.FAMS.util.User.ValidatorUtil;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

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


    @PreAuthorize("hasAnyAuthority('VIEW_USER MANAGEMENT')")
    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(defaultValue = "1") int page) {
        try {
            ApiResponse apiResponse = new ApiResponse();
            PageRequest pageRequest = PageRequest.of(page - 1, 9);

            Page<User> userPage = userService.findAll(pageRequest);

            Page<UserDTOResponse> userDTOPage = userPage.map(user -> userMapper.toResponse(user));

            apiResponse.ok(userDTOPage);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println("biibi");
            throw new ApplicationException();
        }
    }

    @PostMapping
    public ResponseEntity<?> postUser(@Valid @RequestBody UserDTOCreateRequest userDTOCreateRequest,
                                      BindingResult bindingResult) throws ParseException {
        ApiResponse apiResponse = new ApiResponse();

        HashMap<String, Object> token = new HashMap<>();

        // validate
        util.validateCreate(userDTOCreateRequest, bindingResult);

        if (bindingResult.hasErrors()) {
            apiResponse.error(validatorUtil.handleValidationErrors(bindingResult.getFieldErrors()));
            return ResponseEntity.badRequest().body(apiResponse);
        }
        // validate end

        // service
        User user = userService.createUser(userDTOCreateRequest);
        // end service

        //trend token
        var jwtToken = jwtService.generateToken(user); // trend token by user
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(user.getEmail());

        // tra ve token data
        token.put("access token", jwtToken);
        token.put("refresh token", refreshToken.getToken());

        // end trend token

        apiResponse.ok(userMapper.toResponse(user),token);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                         @Valid @RequestBody UserDTOUpdateRequest userDTOUpdateRequest,
                                        BindingResult bindingResult) throws ParseException {

        return userService.updateUser(userDTOUpdateRequest, id,
                userDTOUpdateRequest.getUserPermission(), bindingResult);
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

}
