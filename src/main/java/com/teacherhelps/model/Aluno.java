package com.teacherhelps.model;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Aluno extends Pessoa {
	
	private String alunoAsd;

	public Aluno() {
		super();
	}
	
	public Aluno(Integer codigo, String nome, Long cpf, Long telefone, String email, Endereco endereco,
			LocalDate dataDeCadastro, Integer codigo2, String alunoAsd) {
		super(codigo, nome, cpf, telefone, email, endereco, dataDeCadastro);
		codigo = codigo2;
		this.alunoAsd = alunoAsd;
	}

	public String getAlunoAsd() {
		return alunoAsd;
	}

	public void setAlunoAsd(String alunoAsd) {
		this.alunoAsd = alunoAsd;
	}
	
}
