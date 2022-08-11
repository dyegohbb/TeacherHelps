package com.teacherhelps.service.impl;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teacherhelps.model.Agendamento;
import com.teacherhelps.model.Aluno;
import com.teacherhelps.model.Disponibilidade;
import com.teacherhelps.model.Professor;
import com.teacherhelps.model.dao.AgendamentoDAO;
import com.teacherhelps.model.dao.AlunoDAO;
import com.teacherhelps.model.dao.ProfessorDAO;
import com.teacherhelps.service.AgendamentoService;
import com.teacherhelps.service.ProfessorService;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

	@Autowired
	AlunoDAO alunoDAO;
	
	@Autowired
	ProfessorDAO professorDAO;
	
	@Autowired
	AgendamentoDAO agendamentoDAO;
	
	@Autowired
	ProfessorService professorService;
	
	@Override
	public String agendar(Map<?, ?> payload, Principal principal) {
		Long professorId = ((Number) payload.get("professorId")).longValue();
		Optional<Aluno> alunoOpt = alunoDAO.findByEmail(principal.getName());
		
		if(professorId != null && alunoOpt.isPresent()) {
			Optional<Professor> professorOpt = professorDAO.findById(professorId);
			
			if(professorOpt.isPresent()) {
				try {
					Professor professor = professorOpt.get();
					Aluno aluno = alunoOpt.get();
					Disponibilidade disponibilidade = professorService.prepareDisponibilidade(payload);
					Agendamento agendamento = new Agendamento(aluno, professor, disponibilidade);
					agendamentoDAO.save(agendamento);
					
					List<Disponibilidade> disponibilidades = professor.getDisponibilidade();
					List<Disponibilidade> novasDisp = new ArrayList<>();
					disponibilidades.forEach((disp) -> {
						if(!disponibilidade.getDataInicio().toString().equals(disp.getDataInicio().toString()) 
								&& !disponibilidade.getDataFim().toString().equals(disp.getDataFim().toString())) {
							novasDisp.add(disp);
						}
					});
					
					aluno.setCarteira(aluno.getCarteira() - professor.getValorPorHora());
					professor.setDisponibilidade(novasDisp);
					professorDAO.save(professor);
					return "sucesso";
					
				} catch (Exception e) {
					System.out.printf(e.getMessage(), e);
				}
				
			}
			
		}
		
		return "";
	}

	@Override
	public List<Agendamento> getAgendamentosByUserId(Long userId, String userType) {
		if(userType.equals("aluno")) {
			return agendamentoDAO.findAgendamentosByAlunoId(userId);
		}else if(userType.equals("professor")) {
			return agendamentoDAO.findAgendamentosByProfessorId(userId);
		}
		return null;
	}

	@Override
	public Optional<Agendamento> checarStatusAgendamento(Long agendamentoId) {
		Optional<Agendamento> agendamento = agendamentoDAO.findById(agendamentoId);
		if(agendamento.isPresent()) {
			LocalDateTime intervaloInicial = LocalDateTime.now().plusMinutes(-10);
			LocalDateTime intervaloFinal = LocalDateTime.now().plusMinutes(10);
			if(agendamento.get().getDisponibilidade().getDataInicio().isBefore(intervaloInicial) && !agendamento.get().getStatus().equals("confirmado")
					&& !agendamento.get().getStatus().equals("reembolsado")) {
				agendamento.get().setStatus("atrasado");
				agendamentoDAO.save(agendamento.get());
			}else if(agendamento.get().getDisponibilidade().getDataInicio().isAfter(intervaloInicial) 
					&& agendamento.get().getDisponibilidade().getDataInicio().isBefore(intervaloFinal) 
					&& !agendamento.get().getStatus().equals("confirmado") && !agendamento.get().getStatus().equals("reembolsado")) {
				agendamento.get().setStatus("disponivel");
				agendamentoDAO.save(agendamento.get());
			}
		}
		return agendamento;
	}

	@Override
	public Optional<Agendamento> confirmarAgendamento(Long agendamentoId) {
		Optional<Agendamento> agendamento = agendamentoDAO.findById(agendamentoId);
		if(agendamento.isPresent()) {
			if(agendamento.get().getStatus().equals("disponivel")) {
				agendamento.get().setStatus("confirmado");
				Professor professor = agendamento.get().getProfessor();
				professor.setCarteira(professor.getCarteira() + professor.getValorPorHora());
				agendamentoDAO.save(agendamento.get());
			}
		}
		return agendamento;
	}

	@Override
	public String reembolsarAgendamento(Long agendamentoId) throws Exception {
		Optional<Agendamento> agendamento = agendamentoDAO.findById(agendamentoId);
		
		if(agendamento.isPresent()) {
			if(!agendamento.get().getStatus().equals("confirmado") && !agendamento.get().getStatus().equals("reembolsado")) {
				Aluno aluno = agendamento.get().getAluno();
				Double valorPorHora = agendamento.get().getProfessor().getValorPorHora();
				aluno.setCarteira(aluno.getCarteira() + valorPorHora);
				agendamento.get().setStatus("reembolsado");
				
				try {
					agendamentoDAO.save(agendamento.get());
					return "reembolsado";
				} catch (Exception e) {
					System.out.printf(e.getMessage(), e);
				}
			}
			
		}
		
		return "";
	}

}
