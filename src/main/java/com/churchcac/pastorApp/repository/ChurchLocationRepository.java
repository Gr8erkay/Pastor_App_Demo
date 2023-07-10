package com.churchcac.pastorApp.repository;

import com.churchcac.pastorApp.entity.ChurchLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChurchLocationRepository extends JpaRepository<ChurchLocation, Integer> {

    Optional<ChurchLocation> findByAddress(String address);
    Optional<ChurchLocation> findByName(String name);
    Optional<ChurchLocation> findById(Long id);
}
