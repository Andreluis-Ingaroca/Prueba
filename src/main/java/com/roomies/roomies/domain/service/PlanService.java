package com.roomies.roomies.domain.service;

import com.roomies.roomies.domain.model.Plan;
import com.roomies.roomies.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PlanService {
    Page<Plan> getAllPlans(Pageable pageable);
    Plan getPlanById(Long planId);
    Plan createPlan(Plan plan);
    Plan updatePlan(Long planId,Plan planRequest);
    ResponseEntity<?> deletePlan(Long planId);

    Plan getPlanByName(String name);
}
