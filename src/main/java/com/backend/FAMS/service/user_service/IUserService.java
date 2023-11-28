package com.backend.FAMS.service.user_service;

import com.backend.FAMS.dto.api_response.ApiResponse;
import com.backend.FAMS.dto.auth_dto.UserLoginByEmailDTORequest;
import com.backend.FAMS.dto.user_request.UserChangePass;
import com.backend.FAMS.dto.user_request.UserDTOCreateRequest;
import com.backend.FAMS.dto.user_request.UserDTOUpdateRequest;
import com.backend.FAMS.dto.user_request.UserLoginDTORequest;
import com.backend.FAMS.dto.user_response.UserDTOResponse;
import com.backend.FAMS.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    ResponseEntity<?> createUser(Authentication authentication, UserDTOCreateRequest userDTOCreateRequest, BindingResult bindingResult);

    ResponseEntity<UserDTOResponse> updateUser(Authentication authentication, UserDTOUpdateRequest userDTOUpdateRequest,
                                               Long id, BindingResult bindingResult);

    ResponseEntity<UserDTOResponse> getUser(Long id);


    ResponseEntity<?> changePassword(UserChangePass userChangePass, BindingResult bindingResult);

    ResponseEntity<?> login(HttpServletRequest request, UserLoginDTORequest loginDTORequest, BindingResult bindingResult);

    List<UserDTOResponse> searchUsers(List<String> keywords);

    ApiResponse loginByFe(HttpServletRequest request, UserLoginByEmailDTORequest email, BindingResult bindingResult);
}
