package com.teacherhelps.service.impl;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teacherhelps.controller.dto.AlunoDTO;
import com.teacherhelps.controller.dto.ProfessorDTO;
import com.teacherhelps.model.Aluno;
import com.teacherhelps.model.Disponibilidade;
import com.teacherhelps.model.Perfil;
import com.teacherhelps.model.Professor;
import com.teacherhelps.model.dao.ProfessorDAO;
import com.teacherhelps.service.ProfessorService;


@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	ProfessorDAO professorDAO;

	@Override
	public List<Professor> listAll() {
		List<Professor> professores = professorDAO.findAll();
		return professores;
	}

	@Override
	public String salvar(Map<?, ?> professor){
		Professor prof = new Professor(professor);
		prof.setPerfis(new ArrayList<Perfil>(Arrays.asList(new Perfil("ROLE_PROFESSOR"))));
		try {
			professorDAO.save(prof);
			return "Salvo com sucesso";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		return null;
		
	}

	@Override
	public Disponibilidade prepareDisponibilidade(Map<?, ?> payload) {
		Map<?, ?> mapDisponibilidade = (Map<?, ?>) payload.get("disponibilidade");
		Disponibilidade d = new Disponibilidade();
		String inicio = mapDisponibilidade.get("dataInicio").toString().replace("/", "-");
		String fim = mapDisponibilidade.get("dataFim").toString().replace("/", "-");
		String startDate = inicio.substring(0, inicio.length() - 3);
		String endDate = fim.substring(0, fim.length() - 3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime dataInicio = LocalDateTime.parse(startDate, formatter);
		LocalDateTime dataFim = LocalDateTime.parse(endDate, formatter);;
		d.setDataInicio(dataInicio);
		d.setDataFim(dataFim);
		return d;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Disponibilidade> prepareListDisponibilidades(Map<?, ?> payload) {
		
		try {
			ArrayList<LinkedHashMap<?, ?>> disponibilidadeMap = new ArrayList<LinkedHashMap<?, ?>>();
			
			disponibilidadeMap.addAll((Collection<? extends LinkedHashMap<?, ?>>) payload.get("disponibilidade"));
			List<Disponibilidade> disponibilidades = new ArrayList<>();
			disponibilidadeMap.forEach((disponibilidade) -> {
				Disponibilidade d = new Disponibilidade();
				String inicio = disponibilidade.get("dataInicio").toString().replace("T", " ");
				String fim = disponibilidade.get("dataFim").toString().replace("T", " ");
				String startDate = inicio.substring(0, inicio.length() - 3);
				String endDate = fim.substring(0, fim.length() - 3);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime dataInicio = LocalDateTime.parse(startDate, formatter);
				LocalDateTime dataFim = LocalDateTime.parse(endDate, formatter);;
				d.setDataInicio(dataInicio);
				d.setDataFim(dataFim);
				disponibilidades.add(d);
			});
			
			
			return disponibilidades;
		} catch (Exception e) {
			System.out.printf(e.getMessage(), e);
		}
		
		return null;
	}

	@Override
	public Optional<Professor> findById(Long professorId) {
		try {
			Optional<Professor> professor = professorDAO.findById(professorId);
			return professor;
		} catch (Exception e) {
			System.out.printf(e.getMessage(), e);
		}
		return Optional.empty();
	}

	@Override
	public List<Disponibilidade> alterarDisponibilidade(Professor professor, Map<?, ?> disponibilidadesMap) {
		List<Disponibilidade> disponibilidades = this.prepareListDisponibilidades(disponibilidadesMap);
		
		if(disponibilidades != null) {
			professor.setDisponibilidade(disponibilidades);
			try {
				professorDAO.save(professor);
			} catch (Exception e) {
				System.out.printf(e.getMessage(), e);
			}
		}
		
		return disponibilidades;
	}

	@Override
	public Optional<ProfessorDTO> findProfessorInfo(Principal principal) {
		Optional<Professor> professor = professorDAO.findByEmail(principal.getName());
		if(professor.isPresent()) {
			Optional<ProfessorDTO> professorObj = Optional.ofNullable(new ProfessorDTO(professor.get()));
			return professorObj;
		}
		return Optional.empty();
	}
}
