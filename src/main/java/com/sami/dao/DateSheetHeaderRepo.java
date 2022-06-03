package com.sami.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sami.entities.DateSheetHeader;

public interface DateSheetHeaderRepo extends JpaRepository<DateSheetHeader, Integer> {
	@Query("select d from DateSheetHeader d")
	public List<DateSheetHeader> getAllDateSheetHeader();
	
	@Modifying
	@Transactional
	@Query("update DateSheetHeader d set"
			+ " d.dateSheetDate = :dateSheetDate,"
			+ " d.department = :department,"
			+ " d.dateSheetType = :dateSheetType,"
			+ " d.firstShiftTime = :firstShiftTime,"
			+ " d.secondShiftTime = :secondShiftTime")
	public int updateDateSheetHeader(@Param(value="dateSheetDate") String dateSheetDate,
			@Param(value="department") String department,
			@Param(value="dateSheetType") String dateSheetType,
			@Param(value="firstShiftTime") String firstShiftTime,
			@Param(value="secondShiftTime") String secondShiftTime);
}
