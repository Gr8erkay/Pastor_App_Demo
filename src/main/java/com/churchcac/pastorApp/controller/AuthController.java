package com.churchcac.pastorApp.controller;

import com.churchcac.pastorApp.model.requestDTO.AuthenticationRequest;
import com.churchcac.pastorApp.model.requestDTO.UserRequestDTO;
import com.churchcac.pastorApp.model.responseDTO.AppResponse;
import com.churchcac.pastorApp.model.responseDTO.AuthenticationResponse;
import com.churchcac.pastorApp.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j

public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<?> createAccount(

            @RequestBody UserRequestDTO registerRequest) {
      log.info("creating");
        return ResponseEntity.ok(AppResponse.buildSuccessTxn(authService.createAccount(registerRequest)));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest) {
     log.info("authentication");
        return ResponseEntity.ok(authService.authenticate(authenticationRequest));
    }

    @GetMapping("/logout")
        public ResponseEntity<AppResponse<String>> logout(HttpServletRequest request, HttpServletResponse response) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                new SecurityContextLogoutHandler().logout(request, response, authentication);
            }
            return ResponseEntity.ok(AppResponse.buildSuccess("redirect:/login"));

}

}

