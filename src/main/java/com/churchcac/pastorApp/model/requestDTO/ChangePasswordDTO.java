package com.churchcac.pastorApp.model.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%*_+()^!^&+=])(?=.*[a-zA-Z]).{7,}$")
    private String oldPassword;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%*_+()^!^&+=])(?=.*[a-zA-Z]).{7,}$")
    private String newPassword;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%*_+()^!^&+=])(?=.*[a-zA-Z]).{7,}$")
    private String confirmPassword;

}
