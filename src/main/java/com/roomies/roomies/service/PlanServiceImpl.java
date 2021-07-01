package com.roomies.roomies.service;

import com.roomies.roomies.domain.model.Plan;
import com.roomies.roomies.domain.repository.PlanRepository;
import com.roomies.roomies.domain.service.PlanService;
import com.roomies.roomies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public Page<Plan> getAllPlans(Pageable pageable) {
        return planRepository.findAll(pageable);
    }

    @Override
    public Plan getPlanById(Long planId) {
        return planRepository.findById(planId)
                .orElseThrow(()->new ResourceNotFoundException("Plan","Id",planId));
    }

    @Override
    public Plan createPlan(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public Plan updatePlan(Long planId, Plan planRequest) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(()->new ResourceNotFoundException("Plan","Id",planId));
        plan.setDescription(planRequest.getDescription())
                .setName(planRequest.getName())
                .setPrice(planRequest.getPrice());
        return planRepository.save(plan);
    }

    @Override
    public ResponseEntity<?> deletePlan(Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(()->new ResourceNotFoundException("Plan","Id",planId));
        planRepository.delete(plan);
        return ResponseEntity.ok().build();
    }

    @Override
    public Plan getPlanByName(String name) {
        return planRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Plan","Name",name));
    }
}
