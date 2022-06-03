package com.sami.dao;

import java.sql.Date;

import javax.validation.constraints.Null;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sami.entities.BusMaintenance;

public interface BusMaintenanceRepo extends JpaRepository<BusMaintenance, Integer> {
	@Null
	@Query("select SUM (amount) from BusMaintenance p")
	public Integer getAllBusMaintenace();
	
	@Null
	@Query("select SUM (amount) from BusMaintenance p where p.date BETWEEN :startDate AND :endDate")
	public Integer getAllBusMaintenaceByDate(@Param("startDate")Date startDate,@Param("endDate")Date endDate);

}
