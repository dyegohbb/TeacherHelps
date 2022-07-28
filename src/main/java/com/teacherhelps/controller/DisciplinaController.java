package com.teacherhelps.controller;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teacherhelps.model.Disciplina;

@RestController
@RequestMapping(value = "/disciplina")
public class DisciplinaController {

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<HashMap<Disciplina, String>> listAll() throws Exception {
		Disciplina[] enums = Disciplina.values();
		
		HashMap<Disciplina, String> enumsMap = new HashMap<Disciplina, String>();
		
		for (Disciplina disciplina : enums) {
			enumsMap.put(disciplina, disciplina.getDisciplina());
		}
		return ResponseEntity.ok().body(enumsMap);
	}
	
}
