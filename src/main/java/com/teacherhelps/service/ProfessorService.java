package com.teacherhelps.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.json.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.teacherhelps.model.Disponibilidade;
import com.teacherhelps.model.Professor;

public interface ProfessorService {

	public List<Professor> listAll();
		
	public String salvar(Map<?, ?> professor) throws JsonMappingException, JsonProcessingException, ParseException;

	Disponibilidade prepareDisponibilidade(Map<?, ?> payload);

	public Optional<Professor> findById(Long professorId);

	List<Disponibilidade> prepareListDisponibilidades(Map<?, ?> payload);

	public List<Disponibilidade> alterarDisponibilidade(Professor professor, Map<?, ?> disponibilidadesMap);
}
