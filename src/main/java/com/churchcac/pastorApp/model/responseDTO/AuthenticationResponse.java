package com.churchcac.pastorApp.model.responseDTO;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private String token;
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phoneNumber;

}
