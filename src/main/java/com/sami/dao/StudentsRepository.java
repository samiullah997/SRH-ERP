package com.sami.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sami.entities.Students;

public interface StudentsRepository extends JpaRepository<Students, Integer> {

	@Query("select s from Students s")
	public Page<Students> getAllStudents(Pageable pageable);
	
	@Query("select s from Students s where s.id =:id")
	public Students getStudentsById(@Param("id")int id);
	
	@Query("select s from Students s where s.cnicNo =:cnicNo")
	public Students getStudentsByCNIC(@Param("cnicNo")String cnicNo);

	@Query("select s from Students s group by s.batch")
	public Page<Students> getAllStudentsBatch(Pageable pageable);

	@Query("select s from Students s group by s.batch")
	public List<Students> getAllListOfBatchRecord();

	@Query("select s from Students s where s.batch = :batch order by s.rollNo")
	public List<Students> getAllStudentsByBatch(@Param("batch") String batch);

	@Query("select COUNT(id) from Students s where s.batch = :batch order by s.id")
	public String getStudentNoByBatch(@Param("batch")String batch);
	 
	@Query("select s from Students s where s.batch = :firstBatch OR s.batch = :secondBatch  order by s.rollNo")
	public List<Students> getAllStudentsByBatch(@Param("firstBatch") String firstBatch,
			@Param("secondBatch") String secondBatch);

}
