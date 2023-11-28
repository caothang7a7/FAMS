package com.backend.FAMS.controller.auth_controller;

import com.backend.FAMS.dto.api_response.ApiResponse;
import com.backend.FAMS.dto.auth_dto.RefreshTokenRequest;
import com.backend.FAMS.dto.auth_dto.UserLoginByEmailDTORequest;
import com.backend.FAMS.dto.user_request.UserLoginDTORequest;
import com.backend.FAMS.service.refreshtoken_service.RefreshTokenService;
import com.backend.FAMS.service.user_service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("refreshToken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest, BindingResult bindingResult) {

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.ok(refreshTokenService.verifyRefreshToken(refreshTokenRequest, bindingResult));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(HttpServletRequest request, @Valid @RequestBody UserLoginDTORequest loginDTORequest, BindingResult bindingResult) {


        return userService.login(request, loginDTORequest, bindingResult);
    }

    @PostMapping("login-by-fe")
    public ResponseEntity<?> loginByFe(HttpServletRequest request, @Valid @RequestBody UserLoginByEmailDTORequest userLoginByEmailDTORequest, BindingResult bindingResult, HttpServletResponse response) {
        ApiResponse apiResponse = userService.loginByFe(request, userLoginByEmailDTORequest, bindingResult);
        // Kiểm tra nếu bạn muốn trả về cookie
        /*if (apiResponse != null && apiResponse.getMetadata() != null) {
            // Lấy thông tin từ metadata (ví dụ: access token)
                Map<String, Object> tokenMap =apiResponse.getMetadata();

            // Lấy giá trị của "access token" từ HashMap
            String accessToken = (String) tokenMap.get("access token");

            // Tạo một cookie mới và thêm vào phản hồi HTTP
            Cookie cookieAccsess = new Cookie("accessToken", accessToken);
            cookieAccsess.setMaxAge(3600); // Đặt thời gian sống của cookie (ví dụ: 3600 giây)
            response.addCookie(cookieAccsess);

            // Lấy giá trị của "refresh token" từ HashMap
            String refreshToken = (String) tokenMap.get("refresh token");
            // Tạo một cookie mới và thêm vào phản hồi HTTP
            Cookie cookieRefresh = new Cookie("refreshToken", refreshToken);
            cookieRefresh.setMaxAge(24 * 24 * 60 * 60); // Đặt thời gian sống của cookie (ví dụ: 24 ngày)
            response.addCookie(cookieRefresh);

        }*/

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
