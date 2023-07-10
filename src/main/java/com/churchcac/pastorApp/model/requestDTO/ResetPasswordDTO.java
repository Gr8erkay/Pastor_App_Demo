package com.churchcac.pastorApp.model.requestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
public class ResetPasswordDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%*_+()^!^&+=])(?=.*[a-zA-Z]).{7,}$")
    private String newPassword;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%*_+()^!^&+=])(?=.*[a-zA-Z]).{7,}$")
    private String confirmPassword;
}