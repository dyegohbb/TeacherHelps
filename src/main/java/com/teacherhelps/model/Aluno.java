package com.teacherhelps.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class Aluno extends Pessoa {
	
	private static final long serialVersionUID = 7305762450475461065L;
	
	private String alunoAsd;

	public Aluno() {
		super();
	}
	
	public Aluno(Integer codigo, String nome, Long cpf, Long telefone, String email, Endereco endereco,
			LocalDateTime dataDeCadastro, Integer codigo2, String alunoAsd) {
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
