package com.teacherhelps.service;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.teacherhelps.model.Agendamento;

public interface AgendamentoService {

	String agendar(Map<?, ?> payload, Principal principal);

	List<Agendamento> getAgendamentosByUserId(Long userId, String userType);

	Optional<Agendamento> checarStatusAgendamento(Long agendamentoId);

	Optional<Agendamento> confirmarAgendamento(Long agendamentoId);

	String deletarAgendamento(Long agendamentoId) throws Exception;
}
