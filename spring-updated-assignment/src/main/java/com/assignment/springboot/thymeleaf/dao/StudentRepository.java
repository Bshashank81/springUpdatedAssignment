package com.assignment.springboot.thymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.springboot.thymeleaf.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	//no extra coding is required here
}
