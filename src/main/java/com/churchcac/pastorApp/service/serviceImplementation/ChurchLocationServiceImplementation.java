package com.churchcac.pastorApp.service.serviceImplementation;

import com.churchcac.pastorApp.entity.ChurchLocation;
import com.churchcac.pastorApp.exception.BadRequestException;
import com.churchcac.pastorApp.exception.ResourceNotFoundException;
import com.churchcac.pastorApp.model.requestDTO.ChurchLocationRequestDTO;
import com.churchcac.pastorApp.model.responseDTO.ChurchLocationResponseDTO;
import com.churchcac.pastorApp.repository.ChurchLocationRepository;
import com.churchcac.pastorApp.service.ChurchLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChurchLocationServiceImplementation implements ChurchLocationService {

    private final ChurchLocationRepository churchLocationRepository;

    @Override
    public ChurchLocationResponseDTO addChurchLocation(ChurchLocationRequestDTO churchLocationRequest) {


        log.info("Creating new church", churchLocationRequest.getName());

        ChurchLocation church = ChurchLocation.builder()
                .name(churchLocationRequest.getName())
                .address(churchLocationRequest.getAddress())
                .build();
        log.info("saving new church location");

        ChurchLocation newChurch = churchLocationRepository.save(church);
        log.info("saved new church");

        return ChurchLocationResponseDTO.builder()
                .name(newChurch.getName())
                .address(newChurch.getAddress())
                .build();
    }

 @Override
    public ChurchLocationResponseDTO viewChurchLocation(Long id) {

        Optional<ChurchLocation> existingChurch = churchLocationRepository.findById(id);

        if (existingChurch.isPresent()) {
            ChurchLocation foundChurch = existingChurch.get();
            return ChurchLocationResponseDTO.builder()
                    .name(foundChurch.getName())
                    .address(foundChurch.getAddress())
                    .build();
        }else {
            throw new ResourceNotFoundException("Church not found");
        }
    }

    @Override
    public ChurchLocationResponseDTO updateChurchLocation(Long id, ChurchLocationRequestDTO churchLocationRequest) {
        ChurchLocation oldChurch = churchLocationRepository.findById(id).orElseThrow(() ->
                new BadRequestException("Invalid Component"));

        oldChurch.setName(churchLocationRequest.getName());
        oldChurch.setAddress(churchLocationRequest.getAddress());

        churchLocationRepository.save(oldChurch);
        return ChurchLocationResponseDTO.builder()
                .name(oldChurch.getName())
                .address(oldChurch.getAddress())
                .build();
    }
}
