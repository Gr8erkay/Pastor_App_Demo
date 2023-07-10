package com.churchcac.pastorApp.controller;

import com.churchcac.pastorApp.model.requestDTO.ChangePasswordDTO;
import com.churchcac.pastorApp.model.responseDTO.AppResponse;
import com.churchcac.pastorApp.model.responseDTO.UserResponseDTO;
import com.churchcac.pastorApp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("api/v1/user")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;


    @PostMapping("/change-user-password")
    public ResponseEntity<AppResponse<String>> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {

        String str = userService.changePassword(changePasswordDTO);
        return ResponseEntity.ok(AppResponse.buildSuccess("login with your new password"));
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserResponseDTO> getUser(Authentication authentication) {
        try {
            UserResponseDTO user = userService.getUserFromAuthentication(authentication);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            // Handle any exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
