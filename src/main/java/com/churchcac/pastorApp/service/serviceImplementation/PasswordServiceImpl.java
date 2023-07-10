package com.churchcac.pastorApp.service.serviceImplementation;

import com.churchcac.pastorApp.entity.User;
import com.churchcac.pastorApp.exception.CustomException;
import com.churchcac.pastorApp.model.requestDTO.ResetPasswordDTO;
import com.churchcac.pastorApp.repository.UserRepository;
import com.churchcac.pastorApp.service.PasswordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PasswordServiceImpl implements PasswordService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    public String validatePasswordResetToken(String token) {

        return null;
    }

    @Override
    public String applicationUrl(HttpServletRequest request) {
        String frontendServerName = "localhost";
        int frontendServerPort = 5173;
        String frontendContextPath = "/resetpassword";
        //return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

        String frontendUrl = "http://" + frontendServerName + ":" + frontendServerPort + frontendContextPath;

        String backendUrl = request.getRequestURL().toString();
        String backendContextPath = request.getContextPath();

        String frontendRelativePath = backendUrl.replace(backendContextPath, "");

        return frontendUrl;
    }


    /**
     * @param user
     * @param passwordDto
     */

    @Override
    public void changePassword(User user, ResetPasswordDTO passwordDto) {
        if (passwordDto.getNewPassword().equals((passwordDto.getConfirmPassword()))) {
            user.setPassword((passwordEncoder.encode(passwordDto.getNewPassword())));
            userRepository.save(user);
        }
       else { throw new CustomException("Password does not match");}
    }
}