package com.teacherhelps.controller.dto;

public class TokenDto {

	private String token;
	private String type;
	private Long id;

	public TokenDto(String token) {
		this.token = token;
	}

	public TokenDto(String token, Long codigo) {
		this.token = token;
		this.id = codigo;
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
