package com.sami.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sami.entities.VehicleCondition;

public interface VehicleConditionRepo extends JpaRepository<VehicleCondition, Integer> {
	
}
