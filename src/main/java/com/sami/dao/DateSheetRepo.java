package com.sami.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sami.entities.DateSheetRecords;

public interface DateSheetRepo extends JpaRepository<DateSheetRecords, Integer>{
	@Query("select d from DateSheetRecords d")
	public List<DateSheetRecords> getAllDateSheetRecords();
	
	@Modifying
	@Transactional
	@Query("update DateSheetRecords d set"
			+ " d.firstPaperNameOne = :firstPaperNameOne,"
			+ " d.firstPaperNameTwo = :firstPaperNameTwo,"
			+ " d.secondPaperNameOne = :secondPaperNameOne,"
			+ " d.secondPaperNameTwo = :secondPaperNameTwo,"
			+ " d.semester = :semester,"
			+ " d.date = :date"
			+ " where d.id= :id")
	public int updateDateSheetRecords(@Param(value="id") int id,
			@Param(value="firstPaperNameOne") String firstPaperNameOne,
			@Param(value="firstPaperNameTwo") String firstPaperNameTwo,
			@Param(value="secondPaperNameOne") String secondPaperNameOne,
			@Param(value="secondPaperNameTwo") String secondPaperNameTwo,
			@Param(value="semester") String semester,
			@Param(value="date") String date);
}
