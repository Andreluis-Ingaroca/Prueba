package com.roomies.roomies.domain.repository;

import com.roomies.roomies.domain.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    public Optional<Profile> findByName(String name);
}
