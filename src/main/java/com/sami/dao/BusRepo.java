package com.sami.dao;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sami.entities.Bus;

public interface BusRepo extends JpaRepository<Bus, Integer>{
	@Query("select b from Bus b where b.busId = :busId")
	public List<Bus> getBusRecordById(@Param(value ="busId") int busId);
	
	@Null
	@Query("select SUM(busSeat) from Bus")
	public Integer getTotalBusSeats();
	
	@Query("select b from Bus b where b.busId = :busId")
	public Bus getBusTotalSeatsById(@Param(value ="busId") int busId);
	
	@Modifying
	@Transactional
	@Query("update Bus b set b.asignStatus = :asignStatus WHERE b.busId = :busId")
	public int updateBusStatusById(@Param(value="busId") int busId,@Param(value="asignStatus") String asignStatus);
}
