package com.sami.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sami.entities.BatchRecord;

public interface BatchRecodeRepo extends JpaRepository<BatchRecord, Integer> {
	
	@Query("select b from BatchRecord b")
	public List<BatchRecord> getAllBatchRecords();
	
	@Query("select b from BatchRecord b")
	public Page<BatchRecord> getAllBatchRecords(Pageable pageable);
}
