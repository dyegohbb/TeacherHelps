package com.teacherhelps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teacherhelps.model.dao.AlunoDAO;
import com.teacherhelps.service.AlunoService;

@Service
public class AlunoServiceImpl implements AlunoService {
	
	@Autowired
	AlunoDAO alunoDAO;

}
