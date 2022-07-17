package com.teacherhelps.service;

import java.util.List;

import com.teacherhelps.model.Professor;

public interface ProfessorService {

	public List<Professor> listAll();
	
	public void salvar(Professor professor);
	
}
