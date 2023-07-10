package com.churchcac.pastorApp.service;


import com.churchcac.pastorApp.model.requestDTO.AuthenticationRequest;
import com.churchcac.pastorApp.model.requestDTO.ForgetPasswordDto;
import com.churchcac.pastorApp.model.requestDTO.ResetPasswordDTO;
import com.churchcac.pastorApp.model.requestDTO.UserRequestDTO;
import com.churchcac.pastorApp.model.responseDTO.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public interface AuthService {
    String forgotPassword(ForgetPasswordDto forgetPasswordDto, HttpServletRequest request);

    String resetPassword(ResetPasswordDTO resetPasswordDTO);

    Object createAccount(UserRequestDTO registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

}
