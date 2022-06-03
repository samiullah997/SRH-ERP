package com.sami.dao;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sami.entities.AccountingRecord;

public interface AccountingRepo extends JpaRepository<AccountingRecord, Integer> {
	@Null
	@Query("SELECT SUM(totalAmount) FROM AccountingRecord where type = 'Registration Fee'")
    public Integer getTotalRegistrationFee();
	
	@Query("select a from AccountingRecord a where a.type = :type AND a.semester = :semester AND a.studentsId.id = :id")
	public AccountingRecord getAllStudentsFeeRecordByRollNoAndSemester(@Param(value="type") String type,@Param(value="semester") String semester,@Param(value="id") int id);
	
	@Null
	@Query("SELECT SUM(totalAmount) FROM AccountingRecord where type = 'Registration Fee' AND date BETWEEN :startDate AND :endDate")
    public Integer getTotalRegistrationFeeByDate(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	@Null
	@Query("SELECT SUM(totalAmount) FROM AccountingRecord where type = 'Semester Fee'")
    public Integer getTotalSemesterFee();
	
	@Null
	@Query("SELECT SUM(totalAmount) FROM AccountingRecord where type = 'Semester Fee' AND date BETWEEN :startDate AND :endDate")
    public Integer getTotalSemesterFeeByDate(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	@Modifying
	@Transactional
	@Query("update AccountingRecord a set a.submitAmount = :submitAmount WHERE a.accountId = :id ")
	public int updateFeeRecord(@Param(value="submitAmount")int submitAmount,@Param(value="id") int id);
	
	@Query("select a from AccountingRecord a where a.studentsId.batch = :batch AND a.semester = :semester order by a.date desc")
	public Page<AccountingRecord> getAllStudentsFeeRecord(Pageable pageable,@Param(value="batch") String batch,@Param(value="semester") String semester);
	
	
 // SearchAlgorithm
	@Query("SELECT a FROM AccountingRecord a WHERE a.studentsId.firstName LIKE :name AND a.semester = :semester AND a.studentsId.batch =:batch OR a.studentsId.lastName LIKE :name AND a.semester = :semester AND a.studentsId.batch =:batch OR a.studentsId.rollNo LIKE :name AND a.semester = :semester AND a.studentsId.batch =:batch")
	public List<AccountingRecord> findByNameContaining(@Param("name")String name,@Param("semester")String semester,@Param("batch")String batch);
	
}
