package com.teacherhelps.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teacherhelps.config.security.TokenService;
import com.teacherhelps.controller.dto.TokenDto;
import com.teacherhelps.controller.form.LoginForm;
import com.teacherhelps.model.Aluno;
import com.teacherhelps.model.Pessoa;
import com.teacherhelps.model.Professor;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@CrossOrigin()
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			Pessoa logado = (Pessoa) authentication.getPrincipal();
			String token = tokenService.gerarToken(authentication);
			TokenDto tokenDTO = new TokenDto(token, logado.getCodigo());
			if(logado.getClass().getSimpleName() == Aluno.class.getSimpleName()) {
				tokenDTO.setType("aluno");
			}else if(logado.getClass().getSimpleName() == Professor.class.getSimpleName()) {
				tokenDTO.setType("professor");
			}
			
			return ResponseEntity.ok(tokenDTO);
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
