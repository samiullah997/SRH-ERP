package com.sami.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sami.entities.Book;

public interface BookRepo extends JpaRepository<Book, Integer>{

	@Query("select b from Book b order by b.id")
	public Page<Book> getAllBooks(Pageable pageable);
	
	@Query("select b from Book b where b.id = :id")
	public Page<Book> getBooksById(Pageable pageable,@Param(value="id") int id);
	
	@Modifying
	@Transactional
	@Query("update Book g set g.availibile = :availibile WHERE g.id = :id")
	public int updateBookRecord(@Param(value="availibile") String availibile,@Param(value="id") int id);
	
	@Modifying
	@Transactional
	@Query("update Book g set g.quantity = :quantity WHERE g.id = :id")
	public int updateBookRecordOfQuantity(@Param(value="quantity") int quantity,@Param(value="id") int id);
	
//	SearchAlgorithm 
//	@Query("SELECT * FROM ResultList WHERE rollNo LIKE :rollNo")
	public List<Book> findByTitleContaining(String title);


}
