package com.sami.dao;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sami.entities.GPARecord;

public interface GPARecordRepo extends JpaRepository<GPARecord, Integer>{
	
	@Null
	@Query("select g from GPARecord g where g.semester = :semester AND g.batch = :batch order by g.rollNo")
	public List<GPARecord> getAllGPARecordList(@Param("semester") String semester,@Param("batch") String batch);
	
	@Null
	@Query("select g from GPARecord g where g.semester = :semester AND g.rollNo = :rollNo")
	public GPARecord getAllGPARecord(@Param("semester") String semester,@Param("rollNo") String rollNo);
	
	@Modifying
	@Transactional
	@Query("update GPARecord g set g.GPA = :GPA , g.CGPA = :CGPA WHERE g.rollNo = :rollNo")
	public int updateGPARecord(@Param(value="GPA") float GPA,@Param(value="CGPA") float CGPA, @Param(value="rollNo") String rollNo);
	
	@Modifying
	@Transactional
	@Query("update GPARecord g set g.semesterCraditHour = :semesterCraditHour  WHERE g.rollNo = :rollNo AND g.semester = :semester")
	public int updateGPARecordCraditHour(@Param(value="semesterCraditHour") float semesterCraditHour,@Param(value="rollNo") String rollNo, @Param(value="semester") String semester);
}
