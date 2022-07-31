package com.teacherhelps.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teacherhelps.model.Aluno;
import com.teacherhelps.model.dao.AlunoDAO;
import com.teacherhelps.service.AlunoService;

@Service
public class AlunoServiceImpl implements AlunoService {
	
	@Autowired
	AlunoDAO alunoDAO;

	@Override
	public String salvar(Map<?, ?> aluno){
		Aluno alun = new Aluno(aluno);
		alun.setCarteira(50);
		try {
			alunoDAO.save(alun);
			return "Salvo com sucesso";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		return null;
		
	}
}
