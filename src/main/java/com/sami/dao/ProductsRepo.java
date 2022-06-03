package com.sami.dao;

import java.sql.Date;

import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sami.entities.Products;

public interface ProductsRepo extends JpaRepository<Products, Integer> {

	@Query("select p from Products p where p.categoriesId.id = :categoriesId")
	public Page<Products> getAllProducts(Pageable pageable,@Param("categoriesId") int categoriesId);
	
	@Query("select SUM (productPrice) from Products p")
	public Integer getAllTotalProductsPrice();
	
	@Query("select SUM (productPrice) from Products p where p.date BETWEEN :startDate AND :endDate")
	public Integer getAllTotalProductsPriceByDate(@Param(value="startDate") Date startDate,@Param(value="endDate") Date endDate);
	
	@Modifying
	@Transactional
	@Query("update Products p set p.quantity = :quantity WHERE p.id = :id")
	public int updateProductCategoryId(@Param(value="id") int id,@Param(value="quantity") String quantity);
	

}
