package com.churchcac.pastorApp.service;

import com.churchcac.pastorApp.model.requestDTO.ChurchLocationRequestDTO;
import com.churchcac.pastorApp.model.responseDTO.ChurchLocationResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface ChurchLocationService {

    ChurchLocationResponseDTO addChurchLocation(ChurchLocationRequestDTO churchLocationRequestDTO);

    ChurchLocationResponseDTO viewChurchLocation(Long id);

    ChurchLocationResponseDTO updateChurchLocation(Long id, ChurchLocationRequestDTO churchLocationRequestDTO);
}
