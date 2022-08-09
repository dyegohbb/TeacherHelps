package com.teacherhelps.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherhelps.model.Professor;

public interface ProfessorDAO extends JpaRepository<Professor, Long>{

	Optional<Professor> findByEmail(String email);
	
	Optional<Professor> findByCodigo(Long codigo);

}
