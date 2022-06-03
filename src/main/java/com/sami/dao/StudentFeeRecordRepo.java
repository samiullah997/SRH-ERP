package com.sami.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sami.entities.StudentFeeRcord;

public interface StudentFeeRecordRepo extends JpaRepository<StudentFeeRcord,Integer>{
	
	@Query("select s from StudentFeeRcord s where s.rollNo = :rollNo")
	public StudentFeeRcord getAllStudentsByRollNo(@Param("rollNo")String rollNo);
	
	@Query("select SUM (totalFee) from StudentFeeRcord s")
	public Integer getGrossIncome();
	
	@Query("select SUM (totalFee) from StudentFeeRcord s where s.feeSumbmittedDate BETWEEN :startDate AND :endDate")
	public Integer getGrossIncomeByDate(@Param("startDate")String startDate,@Param("endDate")String endDate);
	
	@Query("select SUM (submittedFee) from StudentFeeRcord s")
	public Integer getNetIncome();
	
	@Query("select s from StudentFeeRcord s")
	public Page<StudentFeeRcord> getAllStudents(Pageable pageable);
	

}