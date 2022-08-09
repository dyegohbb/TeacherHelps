package com.teacherhelps.model;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class Disponibilidade {

	private LocalDateTime dataInicio;
	
	private LocalDateTime dataFim;

	
	public Disponibilidade() {
		super();
	}

	public Disponibilidade(LocalDateTime dataInicio, LocalDateTime dataFim) {
		super();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}
	
}
