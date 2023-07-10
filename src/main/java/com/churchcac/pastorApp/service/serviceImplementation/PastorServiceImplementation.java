package com.churchcac.pastorApp.service.serviceImplementation;

import com.churchcac.pastorApp.entity.Pastor;
import com.churchcac.pastorApp.exception.ResourceCreationException;
import com.churchcac.pastorApp.exception.ResourceNotFoundException;
import com.churchcac.pastorApp.model.requestDTO.EmailValidator;
import com.churchcac.pastorApp.model.requestDTO.PastorRequestDTO;
import com.churchcac.pastorApp.model.responseDTO.PastorResponseDTO;
import com.churchcac.pastorApp.repository.PastorRepository;
import com.churchcac.pastorApp.service.PastorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PastorServiceImplementation implements PastorService {

    private final EmailValidator emailValidator;

    private final PastorRepository pastorRepository;



    @Override
    public PastorResponseDTO addPastor(@RequestBody @Valid PastorRequestDTO pastorRequestDTO) {
        log.info("The code is still working 01");

        boolean isValidEmail = emailValidator.test(pastorRequestDTO.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Email is not valid");
        }

        Optional<Pastor> existingPastor = pastorRepository.findByEmail(pastorRequestDTO.getEmail());
        if (existingPastor.isPresent()) {
            throw new ResourceCreationException("Pastor with email already exists");
        }
        log.info("The code is still working 02");

        Pastor pastor = new Pastor();
        pastor.setEmail(pastorRequestDTO.getEmail());
        pastor.setFirstName(pastorRequestDTO.getFirstName());
        pastor.setLastName(pastorRequestDTO.getLastName());
        pastor.setPhoneNumber(pastorRequestDTO.getPhoneNumber());
        pastor.setAge(pastorRequestDTO.getAge());
        pastor.setMiddleName(pastorRequestDTO.getMiddleName());
        pastor.setDateOfBirth(pastorRequestDTO.getDateOfBirth());
        pastor.setPlaceOfBirth(pastorRequestDTO.getPlaceOfBirth());
        pastor.setStateOfOrigin(pastorRequestDTO.getStateOfOrigin());
        pastor.setHomeAddress(pastorRequestDTO.getHomeAddress());
        pastor.setDateOfOrdination(pastorRequestDTO.getDateOfOrdination());
        pastor.setIsAlive(pastorRequestDTO.getIsAlive());
        pastor.setNationality(pastorRequestDTO.getNationality());
        log.info("The code is still working 03");

        Pastor newPastor = pastorRepository.save(pastor);
        log.info("The code is still working 04");


        return PastorResponseDTO.builder()
                .firstName(newPastor.getFirstName())
                .lastName(newPastor.getLastName())
                .email(newPastor.getEmail())
                .phoneNumber(newPastor.getPhoneNumber())
                .build();
    }


    @Override
    public PastorResponseDTO viewPastor(Long id, PastorRequestDTO pastorRequestDTO) {
        // Retrieve the pastor information based on the provided PastorResponseDTO
        Optional<Pastor> pastor = pastorRepository.findByEmail(pastorRequestDTO.getEmail());

        if (pastor.isPresent()) {
            Pastor foundPastor = pastor.get();
            // Update the PastorResponseDTO with the retrieved information

            return PastorResponseDTO.builder()
                    .firstName(foundPastor.getFirstName())
                    .lastName(foundPastor.getLastName())
                    .email(foundPastor.getEmail())
                    .phoneNumber(foundPastor.getPhoneNumber())
                    .build();
        } else {
            throw new ResourceNotFoundException("Pastor not found");
        }
    }


    @Override
    public PastorResponseDTO updatePastor(Long id, PastorRequestDTO pastorRequestDTO) {
        // Retrieve the pastor from the repository based on the email
        Optional<Pastor> existingPastor = pastorRepository.findByEmail(pastorRequestDTO.getEmail());

        if (existingPastor.isPresent()) {
            Pastor pastor = existingPastor.get();

            // Update the pastor information with the provided details
            pastor.setFirstName(pastorRequestDTO.getFirstName());
            pastor.setLastName(pastorRequestDTO.getLastName());
            pastor.setPhoneNumber(pastorRequestDTO.getPhoneNumber());
            // Update other properties as needed

            // Save the updated pastor in the repository
            Pastor updatedPastor = pastorRepository.save(pastor);

            // Update the PastorResponseDTO with the updated information


            return PastorResponseDTO.builder()
                    .firstName(updatedPastor.getFirstName())
                    .lastName(updatedPastor.getLastName())
                    .email(updatedPastor.getEmail())
                    .phoneNumber(updatedPastor.getPhoneNumber())
                    .build();
        } else {
            throw new ResourceNotFoundException("Pastor not found");
        }
    }

}
