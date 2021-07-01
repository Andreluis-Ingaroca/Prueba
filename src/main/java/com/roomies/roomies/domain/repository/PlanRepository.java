package com.roomies.roomies.domain.repository;

import com.roomies.roomies.domain.model.Plan;
import com.roomies.roomies.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan,Long> {
    public Optional<Plan> findByName(String name);
}
