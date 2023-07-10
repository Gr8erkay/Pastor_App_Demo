package com.churchcac.pastorApp.service;

import com.churchcac.pastorApp.entity.Pastor;
import com.churchcac.pastorApp.model.requestDTO.PastorRequestDTO;
import com.churchcac.pastorApp.model.responseDTO.PastorResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface PastorService {

    PastorResponseDTO addPastor(PastorRequestDTO pastorRequestDTO);

    PastorResponseDTO viewPastor(Long id, PastorRequestDTO pastorRequestDTO);
    PastorResponseDTO updatePastor(Long id, PastorRequestDTO pastorRequestDTO);

}
