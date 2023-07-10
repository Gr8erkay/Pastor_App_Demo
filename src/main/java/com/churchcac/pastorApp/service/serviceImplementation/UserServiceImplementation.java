package com.churchcac.pastorApp.service.serviceImplementation;


import com.churchcac.pastorApp.entity.User;
import com.churchcac.pastorApp.exception.CustomException;
import com.churchcac.pastorApp.model.requestDTO.ChangePasswordDTO;
import com.churchcac.pastorApp.model.responseDTO.UserResponseDTO;
import com.churchcac.pastorApp.repository.UserRepository;
import com.churchcac.pastorApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService {

    private final static String USER_NOT_FOUND_MSG = "User with email: %s not found.";
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }



    @Override
    public Optional<User> findUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    /**
     * @param
     * @return
     */



    @Override
    public String changePassword(ChangePasswordDTO changePasswordDTO) {

            String userEmail= getUserEmail();
            User user= userRepository.findByEmail(userEmail).orElseThrow();
            log.info("find user");
            log.info("{}",user);
        if(!changePasswordDTO.getConfirmPassword().equals(changePasswordDTO.getNewPassword())){
            throw new CustomException("confirm password");
        }

            if(!passwordEncoder.matches(changePasswordDTO.getOldPassword(),user.getPassword())){
            throw new CustomException("Wrong password");
        }

            user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
            userRepository.save(user);
        log.info("{}",user);

        return "Password Updated successfully";
    }

    /**
     * @param
     * @return
     */

    @Override
    public UserResponseDTO getUser(HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization");

        jwtService.validateToken(jwtToken);

        User user = jwtService.extractUserFromToken(jwtToken);


            return UserResponseDTO.builder()
                    .profileName(user.getProfileName())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .phoneNumber(user.getPhoneNumber())
                    .build();
    }

    public UserResponseDTO getUserFromAuthentication(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            throw new IllegalArgumentException("Invalid authentication object or user not found");
        }

        User user = (User) authentication.getPrincipal();
        return UserResponseDTO.builder()
                .profileName(user.getProfileName())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }


    private String getUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){

        }
        return authentication.getName();
    }




    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
