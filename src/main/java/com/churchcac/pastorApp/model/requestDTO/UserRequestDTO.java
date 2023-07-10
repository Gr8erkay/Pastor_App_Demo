package com.churchcac.pastorApp.model.requestDTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotBlank
    @Length
    private String firstName;
    @NotBlank
    @Length(min = 2)
    private String lastName;
    @NotBlank
    @Length(min = 2)
    private String profileName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Length(min = 11)
    private String phoneNumber;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%*_+()^!^&+=])(?=.*[a-zA-Z]).{7,}$")
    private String password;
}
