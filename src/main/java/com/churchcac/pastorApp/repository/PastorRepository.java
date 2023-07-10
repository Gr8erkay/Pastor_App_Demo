package com.churchcac.pastorApp.repository;

import com.churchcac.pastorApp.entity.ChurchLocation;
import com.churchcac.pastorApp.entity.Pastor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PastorRepository extends JpaRepository<Pastor, Integer> {

    Optional<Pastor> findByEmail(String email);
    Optional<Pastor> findById(Integer id);
    Optional<Pastor> findByFirstName(String firstName);
    Optional<Pastor> findByLastName(String lastName);
    Optional<Pastor> findByChurchLocation(ChurchLocation churchLocation);

}
