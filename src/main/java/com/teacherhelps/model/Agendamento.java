package com.teacherhelps.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long codigo;

	@OneToOne
	private Aluno aluno;

	@OneToOne
	private Professor professor;

	private Disponibilidade disponibilidade;
	
	private String status = "agendado";

	public Agendamento() {
	}

	public Agendamento(Aluno aluno, Professor professor, Disponibilidade disponibilidade) {
		this.aluno = aluno;
		this.professor = professor;
		this.disponibilidade = disponibilidade;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disponibilidade getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Disponibilidade disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
