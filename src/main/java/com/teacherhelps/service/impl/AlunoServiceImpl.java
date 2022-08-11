package com.teacherhelps.service.impl;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teacherhelps.controller.dto.AlunoDTO;
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
			System.out.printf(e.getMessage(), e);
		}
	
		return null;
	}

	@Override
	public Optional<AlunoDTO> findAlunoInfo(Principal principal) {
		Optional<Aluno> aluno = alunoDAO.findByEmail(principal.getName());
		if(aluno.isPresent()) {
			Optional<AlunoDTO> alunoObj = Optional.ofNullable(new AlunoDTO(aluno.get()));
			return alunoObj;
		}
		return Optional.empty();
	}
}
