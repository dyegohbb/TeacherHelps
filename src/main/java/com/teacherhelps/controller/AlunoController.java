package com.teacherhelps.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teacherhelps.model.dao.AgendamentoDAO;
import com.teacherhelps.model.dao.AlunoDAO;
import com.teacherhelps.model.dao.ProfessorDAO;
import com.teacherhelps.service.AgendamentoService;
import com.teacherhelps.service.AlunoService;
import com.teacherhelps.service.ProfessorService;

@RestController
@RequestMapping(value = "/aluno")
public class AlunoController {

	@Autowired
	AlunoService alunoService;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	ProfessorDAO professorDAO;
	
	@Autowired
	AlunoDAO alunoDAO;
	
	@Autowired
	AgendamentoDAO agendamentoDAO;
	
	@Autowired
	AgendamentoService agendamentoService;
	
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List<Aluno>> listAll() throws Exception {
//
//		List<Aluno> empresas = null;
//		if (empresas.isEmpty()) {
//			return ResponseEntity.noContent().build();
//		} else {
//			return ResponseEntity.ok().body(empresas);
//		}
//
//	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> cadastrar(@RequestBody Map<?, ?> aluno) throws Exception {
		String status = this.alunoService.salvar(aluno);
		if(status.equals("Salvo com sucesso")) {
			return ResponseEntity.ok().build();
		}else{
			return ResponseEntity.badRequest().body("erro ao cadastrar");
		}
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, value = "/agendar")
	public ResponseEntity<Object> agendar(@RequestBody Map<?, ?> payload, Principal principal) throws Exception {
		String status = agendamentoService.agendar(payload, principal);
		
		if(status.equals("sucesso")) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.badRequest().body("Erro ao agendar");
		
	}
}
