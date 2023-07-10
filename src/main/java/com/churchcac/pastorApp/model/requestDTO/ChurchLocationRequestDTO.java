package com.churchcac.pastorApp.model.requestDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChurchLocationRequestDTO {

    public String name;

    public String address;

    public Integer population;
}
