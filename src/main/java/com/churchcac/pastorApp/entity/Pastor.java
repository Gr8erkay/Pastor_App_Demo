package com.churchcac.pastorApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pastors")
public class Pastor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    private String rank;

    @ManyToOne
    @JoinColumn(name = "church_location")
    private ChurchLocation churchLocation;

}
