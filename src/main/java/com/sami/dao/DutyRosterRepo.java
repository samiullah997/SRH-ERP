package com.sami.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sami.entities.DutyRosterRecord;

public interface DutyRosterRepo extends JpaRepository<DutyRosterRecord,Integer> {

	@Query("select d from DutyRosterRecord d")
	public List<DutyRosterRecord> getAllDutyRosterRecord();

	@Modifying
	@Transactional
	@Query("update DutyRosterRecord d set"
			+ " d.firstTeacherNameOne = :firstTeacherNameOne,"
			+ " d.firstTeacherNameTwo = :firstTeacherNameTwo,"
			+ " d.firstTeacherNameThree = :firstTeacherNameThree,"
			+ " d.secondTeacherNameOne = :secondTeacherNameOne,"
			+ " d.secondTeacherNameTwo = :secondTeacherNameTwo,"
			+ " d.secondTeacherNameThree = :secondTeacherNameThree"
			+ " where d.id = :id")
	public int updateDutyRosterRecord(@Param(value="id") int id,
			@Param(value="firstTeacherNameOne")String firstTeacherNameOne,
			@Param(value="firstTeacherNameTwo")String firstTeacherNameTwo,
			@Param(value="firstTeacherNameThree")String firstTeacherNameThree,
			@Param(value="secondTeacherNameOne")String secondTeacherNameOne,
			@Param(value="secondTeacherNameTwo")String secondTeacherNameTwo,
			@Param(value ="secondTeacherNameThree")String secondTeacherNameThree);
	
	@Modifying
	@Transactional
	@Query("update DutyRosterRecord d set"
			+ " d.date = :date"
			+ " where d.id = :id")
	public int updateDutyRosterDate(@Param(value="id") int id,
			@Param(value = "date")String Date);
	
}
