package com.teacherhelps.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherhelps.model.Pessoa;

public interface PessoaDAO extends JpaRepository<Pessoa, Long> {
	
	Optional<Pessoa> findByEmail(String email);

	Pessoa findByCodigo(Integer codigo);

}
