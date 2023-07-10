package com.churchcac.pastorApp.service.serviceImplementation;

import com.churchcac.pastorApp.entity.User;
import com.churchcac.pastorApp.exception.ResourceCreationException;
import com.churchcac.pastorApp.model.requestDTO.*;
import com.churchcac.pastorApp.model.responseDTO.AuthenticationResponse;
import com.churchcac.pastorApp.model.responseDTO.UserResponseDTO;
import com.churchcac.pastorApp.repository.UserRepository;
import com.churchcac.pastorApp.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService implements AuthService {
    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final EmailValidator emailValidator;


    @Override
    public String forgotPassword(ForgetPasswordDto forgetPasswordDto, HttpServletRequest request) {
        return null;
    }


    @Override
    public UserResponseDTO createAccount(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        log.info("The code is still working 01");

        boolean isValidEmail = emailValidator.test(userRequestDTO.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Email is not valid");
        }
        if (!isPasswordValid(userRequestDTO.getPassword())) {
            throw new IllegalStateException("Password is not valid");
        }
        Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new ResourceCreationException("User with email already exists");
        }
        Optional<User> existingUser2 = userRepository.findByProfileName(userRequestDTO.getProfileName());
        if (existingUser2.isPresent()) {
            throw new ResourceCreationException("User with profileName already exists");
        }
        log.info("The code is still working 02");

        User user = new User();
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setProfileName(userRequestDTO.getProfileName());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        log.info("The code is still working 03");


        User newUser = userRepository.save(user);
        log.info("The code is still working 04");


        return UserResponseDTO.builder()
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .profileName(newUser.getProfileName())
                .email(newUser.getEmail())
                .phoneNumber(newUser.getPhoneNumber())
                .build();
    }



    public static boolean isPasswordValid(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()));

        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        log.info("User token: {}", jwtToken);

        return AuthenticationResponse.builder()
                .id(user.getId())
                .userName(user.getProfileName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .token(jwtToken)
                .build();
    }

    @Override
    public String resetPassword(ResetPasswordDTO resetPasswordDTO) {
        User user= userRepository.findByEmail(resetPasswordDTO.getEmail()).orElseThrow();
        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
        userRepository.save(user);
        return "password reset successful";
    }

    private String generateForgotPasswordTokenUrl(String applicationUrl, String token) {
        String url =
                applicationUrl
                        + "/api/v1/user/reset-password?token="
                        + token;

        log.info("Click the link to Reset your Password: {}",url);
        return url;
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+
                request.getServerName()+
                ":"+
                request.getServerPort()+
                request.getContextPath();
    }

}
