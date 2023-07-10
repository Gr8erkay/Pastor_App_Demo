package com.churchcac.pastorApp.repository;

import com.churchcac.pastorApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    Optional<User> findById(Integer integer);

    Optional<User> findByEmail(String email);
    Optional<User> findByProfileName(String profileName);
}
