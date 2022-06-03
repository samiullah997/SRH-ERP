package com.sami.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sami.entities.Categories;

public interface CategoryRepo extends JpaRepository<Categories, Integer> {

}
