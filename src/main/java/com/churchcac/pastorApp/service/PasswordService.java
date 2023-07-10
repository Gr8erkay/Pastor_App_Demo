package com.churchcac.pastorApp.service;

import com.churchcac.pastorApp.entity.User;
import com.churchcac.pastorApp.model.requestDTO.ResetPasswordDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface PasswordService {
    String validatePasswordResetToken(String token);
    void changePassword(User user, ResetPasswordDTO passwordDto);

    String applicationUrl(HttpServletRequest request);
}