package com.ducphan.identity_service.controller;

import com.ducphan.identity_service.dto.request.ApiResponse;
import com.ducphan.identity_service.dto.request.AuthenticationRequest;
import com.ducphan.identity_service.dto.response.AuthenticationReponse;
import com.ducphan.identity_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationReponse> authenticate(@RequestBody AuthenticationRequest request){
        boolean result =  authenticationService.authentication(request);
        return ApiResponse.<AuthenticationReponse>builder()
                .result(AuthenticationReponse.builder()
                        .authenticated(result)
                        .build())
                .build();
    }

}
