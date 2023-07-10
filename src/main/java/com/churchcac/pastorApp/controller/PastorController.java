package com.churchcac.pastorApp.controller;

import com.churchcac.pastorApp.model.requestDTO.PastorRequestDTO;
import com.churchcac.pastorApp.model.requestDTO.UserRequestDTO;
import com.churchcac.pastorApp.model.responseDTO.AppResponse;
import com.churchcac.pastorApp.model.responseDTO.PastorResponseDTO;
import com.churchcac.pastorApp.model.responseDTO.UserResponseDTO;
import com.churchcac.pastorApp.service.PastorService;
import com.churchcac.pastorApp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/pastor")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PastorController {

    private final PastorService pastorService;

    @GetMapping("/view-pastor/{id}")
    public ResponseEntity<PastorResponseDTO> getPastor(@PathVariable("id")Long id, @RequestBody PastorRequestDTO pastorRequest) {
        try {
            PastorResponseDTO pastor = pastorService.viewPastor(id, pastorRequest);
            return ResponseEntity.ok(pastor);
        } catch (Exception e) {
            // Handle any exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create-pastor")
    public ResponseEntity<?> createPastor(@RequestBody PastorRequestDTO pastorRequest) {
        log.info("creating");
        return ResponseEntity.ok(AppResponse.buildSuccessTxn(pastorService.addPastor(pastorRequest)));
    }

    @PutMapping("/update-pastor/{id}")
    public ResponseEntity<?> updatePastor(@PathVariable("id") Long id, @RequestBody PastorRequestDTO pastorRequest) {
        PastorResponseDTO pastor = pastorService.updatePastor(id, pastorRequest);
        return ResponseEntity.ok(AppResponse.buildSuccess(pastor));
    }

}
