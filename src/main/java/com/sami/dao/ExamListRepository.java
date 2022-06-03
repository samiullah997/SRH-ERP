package com.sami.dao;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sami.entities.ExamList;

public interface ExamListRepository extends JpaRepository<ExamList, Integer> {
	
	@Query("select e from ExamList e where e.exam = :exam")
	public Page<ExamList> getAllExamList(@Param(value = "exam")String exam,Pageable pageable);
	
	@Query("select e from ExamList e where e.exam = :exam AND e.listDate = :listDate")
	public Page<ExamList> getAllExamList(@Param(value = "exam")String exam,@Param(value="listDate") String listDate,Pageable pageable);
	
	
	@Modifying
	@Transactional
	@Query("update ExamList e set e.listDate =:listDate where e.exam = 'New Result'")
	public int updateListeDate(@Param(value="listDate") String listDate);
	
	@Modifying
	@Transactional
	@Query("update ExamList e set e.exam =:exam")
	public int updatePaperStatus(@Param(value="exam") String exam);
	
}
