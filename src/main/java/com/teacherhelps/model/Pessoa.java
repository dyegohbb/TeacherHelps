package com.teacherhelps.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pessoa implements UserDetails {

	private static final long serialVersionUID = -476083596287073030L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long codigo;
	
	private String nome;

	private String cpf;

	private String telefone;
	
	@Column(unique = true)
	private String email;
	
	private String senha;
	
	private double carteira = 0; 
	
	private Integer tentatives = 3;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private boolean ativo = true;
	
	private LocalDateTime dataDesbloqueio;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Perfil> perfis = new ArrayList<Perfil>(Arrays.asList(new Perfil("ROLE_ALUNO")));

	private String endereco;

	private LocalDateTime dataDeCadastro;

	public Pessoa(Long codigo, String nome, String cpf, String telefone, String email, String endereco,
			LocalDateTime dataDeCadastro) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.dataDeCadastro = dataDeCadastro;
	}

	public Pessoa() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public LocalDateTime getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(LocalDateTime dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getTentatives() {
		return tentatives;
	}

	public void setTentatives(Integer tentatives) {
		this.tentatives = tentatives;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDateTime getDataDesbloqueio() {
		return dataDesbloqueio;
	}

	public void setDataDesbloqueio(LocalDateTime dataDesbloqueio) {
		this.dataDesbloqueio = dataDesbloqueio;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	

	public double getCarteira() {
		return carteira;
	}

	public void setCarteira(double carteira) {
		this.carteira = carteira;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.ativo;
	}

}
