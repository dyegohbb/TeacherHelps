package com.teacherhelps.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherhelps.model.Aluno;

public interface AlunoDAO extends JpaRepository<Aluno, Long> {

	Optional<Aluno> findByEmail(String email);

}
