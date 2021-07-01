package com.roomies.roomies.domain.repository;

import com.roomies.roomies.domain.model.Leaseholder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaseholderRepository extends JpaRepository<Leaseholder,Long> {
    public Optional<Leaseholder> findByName(String name);
}
