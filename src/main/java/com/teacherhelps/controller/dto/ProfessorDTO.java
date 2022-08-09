package com.teacherhelps.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.teacherhelps.model.Disciplina;
import com.teacherhelps.model.Disponibilidade;
import com.teacherhelps.model.Perfil;
import com.teacherhelps.model.Professor;

public class ProfessorDTO {

	private Long codigo;
	
	private String nome;

	private String cpf;

	private String telefone;
	
	private List<Disponibilidade> disponibilidades;

	private double valorPorHora;

	private String sobre;

	private Disciplina disciplina;
	
	private String email;
	
	private double carteira; 
	
	private List<Perfil> perfis;

	private String endereco;

	private LocalDateTime dataDeCadastro;

	public ProfessorDTO(Professor professor) {
		this.codigo = professor.getCodigo();
		this.nome = professor.getNome();
		this.cpf = professor.getCpf();
		this.telefone = professor.getTelefone();
		this.disponibilidades = professor.getDisponibilidade();
		this.valorPorHora = professor.getValorPorHora();
		this.sobre = professor.getSobre();
		this.disciplina = professor.getDisciplina();
		this.email = professor.getEmail();
		this.carteira = professor.getCarteira();
		this.perfis = professor.getPerfis();
		this.endereco = professor.getEndereco();
		this.dataDeCadastro = professor.getDataDeCadastro();
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

	public List<Disponibilidade> getDisponibilidades() {
		return disponibilidades;
	}

	public void setDisponibilidades(List<Disponibilidade> disponibilidades) {
		this.disponibilidades = disponibilidades;
	}

	public double getValorPorHora() {
		return valorPorHora;
	}

	public void setValorPorHora(double valorPorHora) {
		this.valorPorHora = valorPorHora;
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
		this.disciplina = disciplina;
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


