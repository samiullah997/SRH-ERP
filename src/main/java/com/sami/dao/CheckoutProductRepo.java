package com.sami.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sami.entities.CheckoutProduct;

public interface CheckoutProductRepo extends JpaRepository<CheckoutProduct, Integer> {

}
