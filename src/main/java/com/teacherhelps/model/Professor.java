package com.teacherhelps.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Professor extends Pessoa {

	@ElementCollection()
	private List<Disponibilidade> disponibilidade;

	private double valorPorHora;

	private String sobre;

	@Enumerated(EnumType.STRING)
	private Disciplina disciplina;

	public Professor() {
		super();
	};
	
	public Professor(Integer codigo, String nome, Long cpf, Long telefone, String email, Endereco endereco,
			LocalDate dataDeCadastro, List<Disponibilidade> disponibilidade, double valorPorHora, String sobre,
			Disciplina disciplina) {
		super(codigo, nome, cpf, telefone, email, endereco, dataDeCadastro);
		this.disponibilidade = disponibilidade;
		this.valorPorHora = valorPorHora;
		this.sobre = sobre;
		this.disciplina= disciplina;
	}

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina= disciplina;
	}

	public double getValorPorHora() {
		return valorPorHora;
	}

	public void setValorPorHora(double valorPorHora) {
		this.valorPorHora = valorPorHora;
	}

	public List<Disponibilidade> getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(List<Disponibilidade> disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

}
