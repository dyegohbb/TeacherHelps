package com.teacherhelps.controller.dto;

import com.teacherhelps.model.Agendamento;
import com.teacherhelps.model.Disponibilidade;

public class AgendamentoDTO {

	private Long codigo;

	private Long alunoId;

	private Long professorId;

	private Disponibilidade disponibilidade;
	
	private String nomeProfessor;
	
	private String nomeAluno;
	
	private Double valorPorHora;
	
	private String status;

	public AgendamentoDTO() {
	}
	
	public AgendamentoDTO(Agendamento agendamento) {
		this.codigo = agendamento.getCodigo();
		this.alunoId = agendamento.getAluno().getCodigo();
		this.professorId = agendamento.getProfessor().getCodigo();
		this.disponibilidade = agendamento.getDisponibilidade();
		this.nomeProfessor = agendamento.getProfessor().getNome();
		this.nomeAluno = agendamento.getAluno().getNome();
		this.valorPorHora = agendamento.getProfessor().getValorPorHora();
		this.status = agendamento.getStatus();
	}

	public AgendamentoDTO(Long codigo, Long alunoId, Long professorId, Disponibilidade disponibilidade) {
		this.codigo = codigo;
		this.alunoId = alunoId;
		this.professorId = professorId;
		this.disponibilidade = disponibilidade;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(Long alunoId) {
		this.alunoId = alunoId;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public Disponibilidade getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Disponibilidade disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Double getValorPorHora() {
		return valorPorHora;
	}

	public void setValorPorHora(Double valorPorHora) {
		this.valorPorHora = valorPorHora;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
