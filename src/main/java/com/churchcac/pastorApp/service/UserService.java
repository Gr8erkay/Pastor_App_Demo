package com.churchcac.pastorApp.service;

import com.churchcac.pastorApp.entity.User;
import com.churchcac.pastorApp.model.requestDTO.ChangePasswordDTO;
import com.churchcac.pastorApp.model.responseDTO.UserResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {

    Optional<User> findUserByEmail(String email);


    String changePassword(ChangePasswordDTO changePasswordDTO);

    UserResponseDTO getUser(HttpServletRequest request);

    UserResponseDTO getUserFromAuthentication(Authentication authentication);

}

