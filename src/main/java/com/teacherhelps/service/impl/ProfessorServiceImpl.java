package com.teacherhelps.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teacherhelps.model.Disponibilidade;
import com.teacherhelps.model.Perfil;
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
	public String salvar(Map<?, ?> professor){
		Professor prof = new Professor(professor);
		prof.setPerfis(new ArrayList<Perfil>(Arrays.asList(new Perfil("ROLE_PROFESSOR"))));
		try {
			professorDAO.save(prof);
			return "Salvo com sucesso";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		return null;
		
	}
}
