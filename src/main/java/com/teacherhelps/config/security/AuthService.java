package com.teacherhelps.config.security;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.teacherhelps.model.Pessoa;
import com.teacherhelps.model.Professor;
import com.teacherhelps.model.dao.AlunoDAO;
import com.teacherhelps.model.dao.PessoaDAO;
import com.teacherhelps.model.dao.ProfessorDAO;

@Service
public class AuthService implements UserDetailsService{
	
	private static Logger logger = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	PessoaDAO pessoaDAO;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Pessoa> pessoa = pessoaDAO.findByEmail(email);
		
		if (pessoa.isPresent()) {
			return pessoa.get();
		}
		
		UsernameNotFoundException ex = new UsernameNotFoundException("Falha ao logar: Usuário ou senha incorretos");
		logger.warn(ex.getMessage());
		throw ex;
	}

}
