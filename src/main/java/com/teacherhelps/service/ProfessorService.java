package com.teacherhelps.service;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.teacherhelps.model.Disponibilidade;
import com.teacherhelps.model.Professor;

public interface ProfessorService {

	public List<Professor> listAll();
	
	public String salvar(Map<?, ?> professor) throws JsonMappingException, JsonProcessingException, ParseException;

}
