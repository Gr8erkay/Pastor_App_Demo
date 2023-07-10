package com.churchcac.pastorApp.model.responseDTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PastorResponseDTO {

    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate dateOfBirth;
    private String placeOfBirth;

    private Integer age;
    private String nationality;
    private String stateOfOrigin;

    private  LocalDate dateOfOrdination;

    private String ordinationNumber;
    private String email;
    private String phoneNumber;

    private String homeAddress;

    private Boolean isAlive;
}
