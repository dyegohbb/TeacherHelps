package com.teacherhelps.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.teacherhelps.model.Aluno;
import com.teacherhelps.model.Perfil;

public class AlunoDTO {
	private Long codigo;
	
	private String nome;

	private String cpf;

	private String telefone;
	
	private double valorPorHora;

	private String email;
	
	private double carteira; 
	
	private List<Perfil> perfis;

	private String endereco;

	private LocalDateTime dataDeCadastro;

	public AlunoDTO(Aluno aluno) {
		this.codigo = aluno.getCodigo();
		this.nome = aluno.getNome();
		this.cpf = aluno.getCpf();
		this.telefone = aluno.getTelefone();
		this.email = aluno.getEmail();
		this.carteira = aluno.getCarteira();
		this.perfis = aluno.getPerfis();
		this.endereco = aluno.getEndereco();
		this.dataDeCadastro = aluno.getDataDeCadastro();
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public double getValorPorHora() {
		return valorPorHora;
	}

	public void setValorPorHora(double valorPorHora) {
		this.valorPorHora = valorPorHora;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getCarteira() {
		return carteira;
	}

	public void setCarteira(double carteira) {
		this.carteira = carteira;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public LocalDateTime getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(LocalDateTime dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}
}


