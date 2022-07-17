package com.teacherhelps.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherhelps.model.Aluno;

public interface AlunoDAO extends JpaRepository<Aluno, Long> {

}
