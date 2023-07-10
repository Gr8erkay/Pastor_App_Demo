package com.churchcac.pastorApp.model.responseDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChurchLocationResponseDTO {

    public String name;

    public String address;

    public Integer population;
}
