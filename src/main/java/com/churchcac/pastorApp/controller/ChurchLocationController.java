package com.churchcac.pastorApp.controller;

import com.churchcac.pastorApp.exception.BadRequestException;
import com.churchcac.pastorApp.model.requestDTO.ChurchLocationRequestDTO;
import com.churchcac.pastorApp.model.responseDTO.AppResponse;
import com.churchcac.pastorApp.model.responseDTO.ChurchLocationResponseDTO;
import com.churchcac.pastorApp.repository.ChurchLocationRepository;
import com.churchcac.pastorApp.service.ChurchLocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/church")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ChurchLocationController {
    private final ChurchLocationRepository churchLocationRepository;

    private final ChurchLocationService churchLocationService;

    @PostMapping("/create-church-location")
    public ResponseEntity<?> createChurchLocation(@RequestBody ChurchLocationRequestDTO churchLocationRequest) {
        if(churchLocationRepository.findByName(churchLocationRequest.getName()).isPresent()){
            throw new BadRequestException("Church with Name Already Exist");
        }

        log.info("{}",churchLocationRequest.getName());
        ChurchLocationResponseDTO newChurch = churchLocationService.addChurchLocation(churchLocationRequest);
        return ResponseEntity.ok(newChurch);
    }

    @PutMapping("/edit-church-location/{id}")
    public ResponseEntity<?> updateChurchLocation(@PathVariable("id") Long id, @RequestBody ChurchLocationRequestDTO churchLocationRequest) {
        ChurchLocationResponseDTO updatedChurch = churchLocationService.updateChurchLocation(id, churchLocationRequest);
        return ResponseEntity.ok(AppResponse.buildSuccess(updatedChurch));
    }

    @GetMapping("/view-church-location/{id}")
    public ResponseEntity<AppResponse<ChurchLocationResponseDTO>> viewChurchLocation(@PathVariable Long id) {
        ChurchLocationResponseDTO  church = churchLocationService.viewChurchLocation(id);
        return ResponseEntity.ok(AppResponse.buildSuccess(church));
    }
}
