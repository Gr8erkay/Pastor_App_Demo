package com.churchcac.pastorApp.model.requestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ForgetPasswordDto {
    @NotBlank
    @Email
    private String email;
}