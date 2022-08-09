package com.teacherhelps.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Professor extends Pessoa {

	private static final long serialVersionUID = -5813873843480847120L;

	@ElementCollection()
	private List<Disponibilidade> disponibilidades;

	private double valorPorHora;

	private String sobre;

	@Enumerated(EnumType.STRING)
	private Disciplina disciplina;

	public Professor() {
		super();
	};
	
	public Professor(Long codigo, String nome, String cpf, String telefone, String email, String endereco,
			LocalDateTime dataDeCadastro, List<Disponibilidade> disponibilidade, double valorPorHora, String sobre,
			Disciplina disciplina) {
		super(codigo, nome, cpf, telefone, email, endereco, dataDeCadastro);
		this.disponibilidades = disponibilidade;
		this.valorPorHora = valorPorHora;
		this.sobre = sobre;
		this.disciplina= disciplina;
	}

	@SuppressWarnings("unchecked")
	public Professor(Map<?, ?> professor) {
		Map<?, ?> prof = (Map<?, ?>) professor.get("professor");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.setSenha(encoder.encode(prof.get("senha").toString()));
		this.setNome(prof.get("nome").toString());
		this.setCpf(prof.get("cpf").toString());
		this.setEmail(prof.get("email").toString());
		this.setSobre(prof.get("sobre").toString());
		this.setValorPorHora(Double.parseDouble(prof.get("valorPorHora").toString()));
		this.setTelefone(prof.get("telefone").toString());
		this.setDataDeCadastro(LocalDateTime.now());
		List<Map<?, ?>> mapDisponibilidades = (List<Map<?, ?>>) prof.get("disponibilidades");
		List<Disponibilidade> disponibilidades = new ArrayList<Disponibilidade>();
		
		for (Map<?, ?> disponibilidade : mapDisponibilidades) {
			Disponibilidade d = new Disponibilidade();
			String inicio = disponibilidade.get("dataInicio").toString().replace("T", " ");
			String fim = disponibilidade.get("dataFim").toString().replace("T", " ");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime dataInicio = LocalDateTime.parse(inicio, formatter);
			LocalDateTime dataFim = LocalDateTime.parse(fim, formatter);;
			d.setDataInicio(dataInicio);
			d.setDataFim(dataFim);
			disponibilidades.add(d);
		}
		this.setDisponibilidade(disponibilidades);
		this.setDisciplina(Disciplina.byValue(prof.get("disciplina").toString()));
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
		return disponibilidades;
	}

	public void setDisponibilidade(List<Disponibilidade> disponibilidade) {
		this.disponibilidades = disponibilidade;
	}

}
