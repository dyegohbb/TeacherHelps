package com.teacherhelps.service;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import com.teacherhelps.controller.dto.AlunoDTO;

public interface AlunoService {

	String salvar(Map<?, ?> aluno);

	Optional<AlunoDTO> findAlunoInfo(Principal principal);
	
}
