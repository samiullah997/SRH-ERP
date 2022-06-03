package com.sami.dao;

import javax.validation.constraints.Null;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sami.entities.SeatBook;

public interface SeatBookRepo extends JpaRepository<SeatBook, Integer> {

	@Null
	@Query(value = "select  count(seatNo) from SeatBook b where b.busId.busId = :busId")
	Integer getBusRecordById(@Param(value ="busId") int busId);
	
	
}
