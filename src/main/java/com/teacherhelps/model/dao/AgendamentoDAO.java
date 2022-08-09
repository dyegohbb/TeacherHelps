package com.teacherhelps.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teacherhelps.model.Agendamento;

public interface AgendamentoDAO extends JpaRepository<Agendamento, Long> {

	@Query(value = "SELECT * FROM agendamento as a where aluno_codigo = :userId order by data_inicio asc", nativeQuery = true)
	List<Agendamento> findAgendamentosByAlunoId(Long userId);
	
	@Query(value = "SELECT * FROM agendamento as a where professor_codigo = :userId order by data_inicio asc", nativeQuery = true)
	List<Agendamento> findAgendamentosByProfessorId(Long userId);

}
