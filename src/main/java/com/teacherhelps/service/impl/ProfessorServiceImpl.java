package com.teacherhelps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teacherhelps.model.Professor;
import com.teacherhelps.model.dao.ProfessorDAO;
import com.teacherhelps.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	ProfessorDAO professorDAO;

	@Override
	public List<Professor> listAll() {
		List<Professor> professores = professorDAO.findAll();
		return professores;
	}

	@Override
	public void salvar(Professor professor) {
		professorDAO.save(professor);
	}
}
