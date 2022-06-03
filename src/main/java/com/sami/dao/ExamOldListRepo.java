package com.sami.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sami.entities.ExamOldList;


public interface ExamOldListRepo extends JpaRepository<ExamOldList, Integer>{
	
	@Query("select e from ExamOldList e")
	public Page<ExamOldList> getAllOldExamList(Pageable pageable);


}
