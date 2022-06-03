package com.sami.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sami.entities.StudentFeedback;

public interface StudentFeedbackRepo extends JpaRepository<StudentFeedback,Integer>{

}
