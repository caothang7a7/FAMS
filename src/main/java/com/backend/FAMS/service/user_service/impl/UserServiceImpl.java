package com.backend.FAMS.service.user_service.impl;

import com.backend.FAMS.dto.api_response.ApiResponse;
import com.backend.FAMS.dto.auth_dto.UserLoginByEmailDTORequest;
import com.backend.FAMS.dto.user_request.UserChangePass;
import com.backend.FAMS.dto.user_request.UserDTOCreateRequest;
import com.backend.FAMS.dto.user_request.UserDTOUpdateRequest;
import com.backend.FAMS.dto.user_request.UserLoginDTORequest;
import com.backend.FAMS.dto.user_response.UserDTOResponse;
import com.backend.FAMS.entity.refreshtoken.RefreshToken;
import com.backend.FAMS.entity.user.User;
import com.backend.FAMS.entity.user.UserPermission;
import com.backend.FAMS.entity.user.user_enum.UserRole;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.exception.OptimisticException;
import com.backend.FAMS.mapper.user_mapper.UserMapper;
import com.backend.FAMS.repository.refreshtoken_repo.RefreshTokenRepository;
import com.backend.FAMS.repository.user_repo.UserPermissionRepository;
import com.backend.FAMS.repository.user_repo.UserRepository;
import com.backend.FAMS.service.gmail_service.ISendMailService;
import com.backend.FAMS.service.jwt_service.IJwtService;
import com.backend.FAMS.service.refreshtoken_service.RefreshTokenService;
import com.backend.FAMS.service.user_service.IUserService;
import com.backend.FAMS.util.UserUtil;
import com.backend.FAMS.util.ValidatorUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserPermissionRepository userPermissionRepository;
    private final UserMapper userMapper;
    private final ValidatorUtil validatorUtil;
    private final UserUtil util;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;
    private final UserDetailsService userDetailsService;
    private final ISendMailService sendMailService;

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
    public ResponseEntity<?> createUser(Authentication authentication, UserDTOCreateRequest userDTOCreateRequest, BindingResult bindingResult) {
            User owner = null;
        if (authentication != null) {
            owner = (User) authentication.getPrincipal();
        }

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
        User user = userMapper.toEntity(userDTOCreateRequest);
        // Tạo mật khẩu ngẫu nhiên
//        String randomPassword = UserUtil.generateRandomPassword();

        user.setPassword(RandomStringUtils.randomAlphanumeric(10));

        UserRole role = UserRole.valueOf(userDTOCreateRequest.getUserPermission());

        UserPermission userPermission = userPermissionRepository.findByRole(role).orElseThrow(
                () -> new NotFoundException("Create fail by not found role")
        );

        user.setUserPermission(userPermission);
        user.setCreatedDate(new Date());
        user.setCreatedBy(owner.getName());

        // password encode
        System.out.println("===============================");
        System.out.println(user.getPassword());
        String password = user.getPassword();
        System.out.println("===============================");

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        //user.setStatus(true);
        //save
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                // Xử lý trường hợp unique constraint violation
                // validate
                util.validateCreate(userDTOCreateRequest, bindingResult);

                if (bindingResult.hasErrors()) {
                    apiResponse.error(validatorUtil.handleValidationErrors(bindingResult.getFieldErrors()));
                    return ResponseEntity.badRequest().body(apiResponse);
                }
            } else {
                // Xử lý các trường hợp ngoại lệ khác
                apiResponse.error(Map.of("error", e.getMessage()));
                return ResponseEntity.badRequest().body(apiResponse);
            }
        }


        // find user_controller created
        User finalUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new NotFoundException("Create fail by not found user_controller")
        );
        // Tạo một ExecutorService với một luồng
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Gửi công việc gửi email đến ExecutorService
        executor.submit(() -> {
            sendMailService.sendMailCreatedUser(finalUser, password);
        });

        // Đóng ExecutorService sau khi đã sử dụng
        executor.shutdown();

        UserDTOResponse userDTOResponse = userMapper.toResponse(user);
        userDTOResponse.setUserPermission(user.getUserPermission().getRole().toString());

        // end service

        //trend token
        var jwtToken = jwtService.generateToken(user); // trend token by user_controller
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(user.getEmail());

        // tra ve token data
        token.put("access token", jwtToken);
        token.put("refresh token", refreshToken.getToken());
        // end trend token

        apiResponse.ok(userDTOResponse, token);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity updateUser(Authentication authentication, UserDTOUpdateRequest userDTOUpdateRequest, Long id,
                                     BindingResult bindingResult) {
        ApiResponse apiResponse = new ApiResponse();
        User owner = null;
        if (authentication != null) {
            owner = (User) authentication.getPrincipal();
        }
        if (bindingResult.hasErrors()) {
            apiResponse.error(validatorUtil.handleValidationErrors(bindingResult.getFieldErrors()));
            return ResponseEntity.badRequest().body(apiResponse);
        }
        util.validate(userDTOUpdateRequest, bindingResult);
        if (bindingResult.hasErrors()) {
            apiResponse.error(validatorUtil.handleValidationErrors(bindingResult.getFieldErrors()));
            return ResponseEntity.badRequest().body(apiResponse);
        }
        final User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found user_controller.")
        );
        try {
            // đổ data theo field
            ReflectionUtils.doWithFields(userDTOUpdateRequest.getClass(), field -> {
                field.setAccessible(true); // Đảm bảo rằng chúng ta có thể truy cập các trường private
                Object newValue = field.get(userDTOUpdateRequest);
                if (newValue != null) { // lấy các giá trị ko null
                    String fieldName = field.getName();
                    // Kiểm tra nếu trường đang xem xét không phải là userPermission
                    if (!fieldName.equals("userPermission")) {
                        Field existingField = ReflectionUtils.findField(user.getClass(), fieldName);
                        if (existingField != null) {
                            existingField.setAccessible(true);
                            ReflectionUtils.setField(existingField, user, newValue);
                        }
                    }
                }
            });
//            user.setStatus(true);
            String roleName = userDTOUpdateRequest.getUserPermission();

            if (roleName != null && roleName.length() > 0) {
                UserRole role = UserRole.valueOf(roleName);
                UserPermission userPermission = userPermissionRepository.findByRole(role).orElseThrow(
                        () -> new NotFoundException("Update failed by not found role.")
                );
                user.setUserPermission(userPermission);
            }

            user.setModifiedDate(new Date());
            user.setModifiedBy(owner.getName());
            UserDTOResponse userDTOResponse = userMapper.toResponse(user);
            userDTOResponse.setUserPermission(user.getUserPermission().getRole().toString());
            apiResponse.ok(userDTOResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (OptimisticLockingFailureException ex) {
            // Xử lý xung đột dữ liệu ở đây
            // Thông báo cho người dùng hoặc thực hiện các hành động cần thiết
            throw new OptimisticException("Data is updated by another user_controller!");
        }


    }

    @Override
    public ResponseEntity getUser(Long id) {
        ApiResponse apiResponse = new ApiResponse();
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found user_controller.")
        );
        UserDTOResponse userDTOResponse = userMapper.toResponse(user);
        userDTOResponse.setUserPermission(user.getUserPermission().getRole().toString());
        apiResponse.ok(userDTOResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changePassword(UserChangePass userChangePass, BindingResult bindingResult) {
        ApiResponse apiResponse = new ApiResponse();
        util.validateCheckConfirmPassword(userChangePass, bindingResult);
        User user = userRepository.findByEmail(userChangePass.getEmail()).orElseThrow(
                () -> new NotFoundException("Not found Email.")
        );
        if (bindingResult.hasErrors()) {
            apiResponse.error(validatorUtil.handleValidationErrors(bindingResult.getFieldErrors()));
            return ResponseEntity.badRequest().body(apiResponse);
        }

        if (!passwordEncoder.matches(userChangePass.getOldPassword(), user.getPassword())) {
            Map<String, String> error = new HashMap<>();
            error.put("oldPassword", "No match");
            apiResponse.setError(error);
            return ResponseEntity.badRequest().body(apiResponse);
        }
        user.setModifiedDate(new Date());
        user.setPassword(passwordEncoder.encode(userChangePass.getNewPassword()));

        userRepository.save(user);

        apiResponse.ok("Change password successfully.");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    @Override
//    public user_controller login(UserLoginDTORequest loginDTORequest) {
//        return null;
//    }

    @Override
    public ResponseEntity<?> login(HttpServletRequest request, UserLoginDTORequest loginDTORequest, BindingResult bindingResult) {

        ApiResponse apiResponse = new ApiResponse();

        User user = userRepository.findByEmail(loginDTORequest.getEmail()).orElseThrow(
                () -> new NotFoundException("user_controller not found with email: " + loginDTORequest.getEmail())
        );
        String oldPassword = passwordEncoder.encode(loginDTORequest.getPassword());
        if (user != null && passwordEncoder.matches(loginDTORequest.getPassword(), user.getPassword()) && !bindingResult.hasErrors()) {

            String jwt = jwtService.generateToken(user);
            if (refreshTokenRepository.findByActiveTrueAndUser_Email(user.getEmail()) != null) {
                refreshTokenService.deleteRefreshToken(refreshTokenRepository.findByActiveTrueAndUser_Email(user.getEmail()));
            }
            RefreshToken refreshToken = refreshTokenService.generateRefreshToken(loginDTORequest.getEmail());
            HashMap<String, Object> token = new HashMap<>();
            token.put("access token", jwt);
            token.put("refresh token", refreshToken.getToken());

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getUsername());

            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenToken);

            }

            UserDTOResponse userDTOResponse = userMapper.toResponse(user);
            userDTOResponse.setUserPermission(user.getUserPermission().getRole().toString());
            apiResponse.ok(user, token);

        } else {

            Map<String, String> error = new HashMap<>();
            error.put("login fail", "Password or email error");
            apiResponse.setError(error);
            return new ResponseEntity<>(apiResponse,HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    public List<UserDTOResponse> searchUsers(List<String> keywords) {
        List<User> users = new ArrayList<>();
        Set<User> uniqueUsers = new HashSet<>();
        for (String keyword : keywords) {
            List<User> results = userRepository.searchByNameOrEmail(keyword);
            uniqueUsers.addAll(results);
        }
        users.addAll(uniqueUsers);
        return users.stream().map(user -> {
            UserDTOResponse userDTOResponse = userMapper.toResponse(user);
            userDTOResponse.setUserPermission(user.getUserPermission().getRole().toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            userDTOResponse.setDob(dateFormat.format(user.getDob()));
            return userDTOResponse;
        }).collect(Collectors.toList());
    }

    @Override
    public ApiResponse loginByFe(HttpServletRequest request, UserLoginByEmailDTORequest userLoginByEmailDTORequest, BindingResult bindingResult) {
        ApiResponse apiResponse = new ApiResponse();
        String email = userLoginByEmailDTORequest.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("user_controller not found with email: " + email)
        );

        String jwt = jwtService.generateToken(user);
        if (refreshTokenRepository.findByActiveTrueAndUser_Email(user.getEmail()) != null) {
            refreshTokenService.deleteRefreshToken(refreshTokenRepository.findByActiveTrueAndUser_Email(user.getEmail()));
        }
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(email);
        HashMap<String, Object> token = new HashMap<>();
        token.put("access token", jwt);
        token.put("refresh token", refreshToken.getToken());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getUsername());

        if (jwtService.isTokenValid(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authenToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authenToken);

        }

        UserDTOResponse userDTOResponse = userMapper.toResponse(user);
        userDTOResponse.setUserPermission(user.getUserPermission().getRole().toString());
        apiResponse.ok(userMapper.toLoginDTOResponse(user), token);

        return apiResponse;
    }


}
