package com.sami.dao;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.sami.entities.ResultList;


public interface PaperRepository extends CrudRepository<ResultList, Integer>, JpaRepository<ResultList , Integer>{
	@Query("select r from ResultList r")
	public Page<ResultList> getAllRsultList(Pageable pageable);
	
	@Query("select r from ResultList r where r.id = :id")
	public Page<ResultList> getAllRsultListById(Pageable pageable,@Param("id")int id);
	
	@Query("select r from ResultList r group by r.rollNo")
	public List<ResultList> getAllRsult();
	
	@Query("select r from ResultList r where r.semester = :semester AND r.batch = :batch group by r.rollNo order by r.rollNo")
	public List<ResultList> getAllRsultListBySemesterAndBatch(@Param("semester")String semester,@Param("batch")String batch);
	
	@Query("select r from ResultList r where r.semester = :semester AND r.batch = :batch AND r.rollNo =:rollNo AND r.paperName =:paperName group by r.rollNo order by r.rollNo")
	public ResultList getAllRsultListBySemesterAndBatchForPaperChecking(@Param("semester")String semester,@Param("batch")String batch,@Param("rollNo")String rollNo,@Param("paperName")String paperName);
	
	@Query(value="select r from ResultList r",nativeQuery = true)
	public List<ResultList> getAllRsultListDataInSingleRowBySemesterAndBatch();
	
	@Query("select r from ResultList r where r.paperName = :paperName AND r.batch = :batch order by r.rollNo")
	public List<ResultList> getAllRsultListByCourse(@Param("paperName")String paperName,@Param("batch")String batch);
	
	@Query("select r from ResultList r where r.batch = :batch AND r.semester = :semester")
	public Page<ResultList> getAllRsultListByPage(Pageable pageable,@Param(value ="batch") String batch,@Param(value ="semester") String semester);
	
	@Query("select r from ResultList r where r.marks BETWEEN '"+50+"' AND '"+59+"'")
	public Page<ResultList> getAllImprovementListStudents(Pageable pageable);
	
	@Query("select r from ResultList r where r.marks < '"+50+"' ")
	public Page<ResultList> getAllRepeaterListStudents(Pageable pageable);
	
	@Query("select COUNT(studentName) from ResultList r where r.marks < '"+50+"'")
	public int getAllRepeaterListStudents();
	
	@Query("select COUNT(studentName) from ResultList r where r.marks >='"+50+"' AND r.marks < '"+60+"'")
	public int getAllImproveListStudents();
	
	@Null
	@Query("SELECT SUM(gp) FROM ResultList where rollNo = :rollNo AND semester = :semester AND batch = :batch")
    Float selectTotalsGradPoints(@Param(value = "rollNo") String rollNo,@Param(value = "semester") String semester,@Param(value = "batch") String batch);

	@Null
	@Query("SELECT SUM(gp) FROM ResultList where rollNo = :rollNo AND batch = :batch")
    Float selectTotalsGradPoints(@Param(value = "rollNo") String rollNo,@Param(value = "batch") String batch);
	
	@Null
	@Query("SELECT SUM(gp) FROM ResultList where rollNo = :rollNo AND batch = :batch AND semester BETWEEN '1st' AND :semester")
    Float selectTotalsGradPointsBySemester(@Param(value = "rollNo") String rollNo,@Param(value = "batch") String batch,@Param(value = "semester") String semester);
	
//	SearchAlgorithm 
//	@Query("SELECT * FROM ResultList WHERE rollNo LIKE :rollNo")
	public List<ResultList> findByStudentNameContainingAndSemesterAndBatch(String StudentName,String semester,String batch);
	
	@Modifying
	@Transactional
	@Query("update ResultList r set r.paperStatus =:paperStatus WHERE r.id =:id")
	public int updatePaperStatus(@Param(value="id") int id, @Param(value="paperStatus") String paperStatus);
	
	@Modifying
	@Transactional
	@Query("update ResultList r set r.marks =:marks,r.gp =:gp,r.value =:value WHERE r.id =:id")
	public int updatePaperMarks(@Param(value="id") int id, @Param(value="marks") int marks, @Param(value="gp") float gp,@Param(value="value") float value);


	

}