package com.teacherhelps.model;

public enum Disciplina {

	LINGUA_PORTUGUESA("Lingua Portuguesa"),
	MATEMATICA("Matemática"),
	BIOLOGIA("Biologia"),
	INGLES("Inglês"),
	ESPANHOL("Espanhol"),
	FRANCES("Francês"),
	HISTORIA("História"),
	GEOGRAFIA("Geografia"),
	ARTES("Artes");
	
	private String disciplina;
	
	Disciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public static Disciplina byValue(String disc) {
		for (Disciplina disciplina : Disciplina.values()) {
			if(disciplina.disciplina.equals(disc)) {
				return disciplina;
			}
		};
		return null;
	}
	
}
