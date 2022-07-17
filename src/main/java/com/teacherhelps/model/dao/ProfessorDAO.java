package com.teacherhelps.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherhelps.model.Professor;

public interface ProfessorDAO extends JpaRepository<Professor, Long>{

}
