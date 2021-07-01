package com.roomies.roomies.domain.repository;

import com.roomies.roomies.domain.model.Landlord;
import com.roomies.roomies.domain.model.Leaseholder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface LandlordRepository extends JpaRepository<Landlord,Long> {
    public Optional<Landlord> findByName(String name);
    public Page<Landlord> findByProvinceAndDepartmentAndDistrict( String province,  String department,  String district, Pageable pageable);
}
