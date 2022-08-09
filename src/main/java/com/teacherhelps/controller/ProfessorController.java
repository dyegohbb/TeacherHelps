package com.teacherhelps.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teacherhelps.controller.dto.ProfessorDTO;
import com.teacherhelps.model.Disponibilidade;
import com.teacherhelps.model.Professor;
import com.teacherhelps.service.ProfessorService;

@RestController
@RequestMapping(value = "/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProfessorDTO>> listAll() throws Exception {
		
		List<Professor> professores = professorService.listAll();
		List<ProfessorDTO> professoresObj = new ArrayList<>();
		for (Professor professor : professores) {
			ProfessorDTO prof = new ProfessorDTO(professor);
			professoresObj.add(prof);
		}
		
		if (professoresObj.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(professoresObj);
		}
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> cadastrar(@RequestBody Map<?, ?> professor) throws Exception {
		String status = this.professorService.salvar(professor);
		if(status.equals("Salvo com sucesso")) {
			return ResponseEntity.ok().build();
		}else{
			return ResponseEntity.badRequest().body("erro ao cadastrar");
		}
	}
	
	@CrossOrigin()
	@RequestMapping(value="/disponibilidades", method = RequestMethod.GET)
	public ResponseEntity<List<Disponibilidade>> listDisponibilidades(@RequestHeader Long professorId ) throws Exception {
		
		Optional<Professor> professor = professorService.findById(professorId);
		List<Disponibilidade> disponibilidades = new ArrayList<>();
		
		if(professor.isPresent()) {
			disponibilidades = professor.get().getDisponibilidade();
		}
		
		if (disponibilidades.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(disponibilidades);
		}
	}
	
	@CrossOrigin()
	@RequestMapping(value="/disponibilidades", method = RequestMethod.POST)
	public ResponseEntity<List<Disponibilidade>> alterarDisponibilidades(@RequestHeader Long professorId, @RequestBody Map<?, ?> disponibilidadesMap) throws Exception {
		Optional<Professor> professor = professorService.findById(professorId);
		List<Disponibilidade> disponibilidades = new ArrayList<>();
		
		if(professor.isPresent()) {
			disponibilidades = professorService.alterarDisponibilidade(professor.get(), disponibilidadesMap);
		}
		
		if (disponibilidades.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().build();
		}
	}
}
