package com.teacherhelps.model;

import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.Entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Aluno extends Pessoa {
	
	private static final long serialVersionUID = 7305762450475461065L;
	
	private String alunoAsd;

	public Aluno() {
		super();
	}
	
	public Aluno(Integer codigo, String nome, String cpf, String telefone, String email, String endereco,
			LocalDateTime dataDeCadastro, Integer codigo2, String alunoAsd) {
		super(codigo, nome, cpf, telefone, email, endereco, dataDeCadastro);
		codigo = codigo2;
		this.alunoAsd = alunoAsd;
	}

	public Aluno(Map<?, ?> aluno) {
		Map<?, ?> prof = (Map<?, ?>) aluno.get("aluno");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.setSenha(encoder.encode(prof.get("senha").toString()));
		this.setNome(prof.get("nome").toString());
		this.setCpf(prof.get("cpf").toString());
		this.setEmail(prof.get("email").toString());
		this.setTelefone(prof.get("telefone").toString());
		this.setDataDeCadastro(LocalDateTime.now());
	}

	public String getAlunoAsd() {
		return alunoAsd;
	}

	public void setAlunoAsd(String alunoAsd) {
		this.alunoAsd = alunoAsd;
	}
	
}
