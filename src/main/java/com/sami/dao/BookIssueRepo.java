package com.sami.dao;


import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sami.entities.BookIssued;
import com.sami.entities.Students;

public interface BookIssueRepo extends JpaRepository<BookIssued, Integer> {
	
	
	@Query("select b from BookIssued b where b.status = 'Issued'")
	public Page<BookIssued> getAllIssuedBooks(Pageable pageable);
	
	@Query("select COUNT(id) from BookIssued b where b.status = 'Issued'")
	public int getTotalIssuedBooks();
	
	@Query("select b from BookIssued b where b.status = 'Issued'")
	public Page<BookIssued> getAllIssuedBooksRecord(Pageable pageable);
	
	@Query("select b from BookIssued b where b.status = 'Issued' AND b.studentsId = :studentsId")
	public Page<BookIssued> getAllIssuedBooksRecordById(Pageable pageable,@Param("studentsId") Students id);
	
	@Query("select b from BookIssued b where b.status = 'Issued' group by b.studentsId")
	public Page<BookIssued> getAllIssuedBooksRecordByGroup(Pageable pageable);
	

	@Modifying
	@Transactional
	@Query("update BookIssued g set g.returnDate = :returnDate,g.status = :status WHERE g.id = :id AND g.status = 'Issued'")
	public int updateBookIssueRecord(@Param(value="returnDate")Date returnDate,@Param(value="status")String status,@Param(value="id") int id);
}
