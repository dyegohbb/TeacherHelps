package com.teacherhelps.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.teacherhelps.model.Pessoa;
import com.teacherhelps.model.dao.PessoaDAO;

public class TokenAuth extends OncePerRequestFilter{

	private TokenService tokenService;
	
	private PessoaDAO pessoaDAO;
	
	public TokenAuth(TokenService tokenService, PessoaDAO pessoaDAO) {
		super();
		this.tokenService = tokenService;
		this.pessoaDAO = pessoaDAO;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);
		if (valido) {
			autenticarUsuario(token);
		}

		filterChain.doFilter(request, response);
		
	}

	private void autenticarUsuario(String token) {
		
		Long idUsuario = tokenService.getIdUsuario(token);
		Pessoa usuario = pessoaDAO.findByCodigo(idUsuario);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getPerfis());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
