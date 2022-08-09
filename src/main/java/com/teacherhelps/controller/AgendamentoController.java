package com.teacherhelps.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teacherhelps.controller.dto.AgendamentoDTO;
import com.teacherhelps.model.Agendamento;
import com.teacherhelps.service.AgendamentoService;


@RestController
@RequestMapping(value = "/agendamentos")
public class AgendamentoController {
	
	@Autowired
	AgendamentoService agendamentoService;
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AgendamentoDTO>> listAll(@RequestHeader Long userId, @RequestHeader String userType) throws Exception {
		List<Agendamento> agendamentos = agendamentoService.getAgendamentosByUserId(userId, userType);
		List<AgendamentoDTO> agendamentosObj = new ArrayList<>();
		agendamentos.forEach((agendamento) ->{
			Optional<Agendamento> agnd = agendamentoService.checarStatusAgendamento(agendamento.getCodigo());
			if(agnd.isPresent()) {
				AgendamentoDTO agendamentoObj = new AgendamentoDTO(agendamento);
				agendamentosObj.add(agendamentoObj);
			}
		});
		
		if (agendamentosObj.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(agendamentosObj);
		}
	}
	
	@CrossOrigin()
	@RequestMapping(value="/check", method = RequestMethod.GET)
	public ResponseEntity<AgendamentoDTO> checkStatusAgendamento(@RequestHeader Long agendamentoId) {
		Optional<Agendamento> agendamento = agendamentoService.checarStatusAgendamento(agendamentoId);
		
		if(agendamento.isPresent()) {
			AgendamentoDTO agendamentoObj = new AgendamentoDTO(agendamento.get());
			return ResponseEntity.ok().body(agendamentoObj);
		}
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin()
	@RequestMapping(value="/confirmar", method = RequestMethod.GET)
	public ResponseEntity<AgendamentoDTO> confirmarAgendamento(@RequestHeader Long agendamentoId) {
		Optional<Agendamento> agendamento = agendamentoService.confirmarAgendamento(agendamentoId);
		
		if(agendamento.isPresent()) {
			AgendamentoDTO agendamentoObj = new AgendamentoDTO(agendamento.get());
			return ResponseEntity.ok().body(agendamentoObj);
		}
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin()
	@RequestMapping(value="/deletar", method = RequestMethod.GET)
	public ResponseEntity<AgendamentoDTO> deletarAgendamento(@RequestHeader Long agendamentoId) throws Exception {
		String status = agendamentoService.deletarAgendamento(agendamentoId);
		
		if(status.equals("deletado")){
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
}
