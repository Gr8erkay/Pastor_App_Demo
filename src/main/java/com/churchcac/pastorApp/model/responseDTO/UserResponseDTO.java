package com.churchcac.pastorApp.model.responseDTO;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private String firstName;
    private String lastName;
    private String profileName;
    private String email;
    private String phoneNumber;

}
