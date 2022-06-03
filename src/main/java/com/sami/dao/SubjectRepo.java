package com.sami.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sami.entities.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Integer> {
	@Query("select s from Subject s where s.semester = :semester")
	public List<Subject> getSubjectBySemester(@Param(value ="semester") String semester);
	
	@Query("select s from Subject s where s.subjectName = :subjectName")
	public Subject getSubjectByPaperName(@Param(value ="subjectName") String subjectName);
	
	@Query("SELECT SUM(subjectCraditHour) FROM Subject where semester = :semester")
    Float selectTotalsCraditHours(@Param(value = "semester") String semester);
	
	@Query("SELECT SUM(subjectCraditHour) FROM Subject where semester BETWEEN :firstSemester AND :secondSemester")
    Float selectTotalsCraditHours(@Param(value = "firstSemester") String firstSemester,@Param(value = "secondSemester") String secondSemester);
	@Query("select s from Subject s order by s.semester")
	public Page<Subject> getAllSubjectOrderBySemester(Pageable pageable);
	
	@Query("select s from Subject s")
	public List<Subject> getAllSubject();
	

}
