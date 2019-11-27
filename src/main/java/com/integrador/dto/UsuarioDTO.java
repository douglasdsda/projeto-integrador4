package com.integrador.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.integrador.entities.Usuario;
import com.integrador.services.validation.UsuarioUpdateValid;

@UsuarioUpdateValid
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "can't be empty")
	@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
	private String nome;
	
	@NotEmpty(message = "can't be empty")
	@Email(message = "invalid email")
	private String email;
	
	private String fotoPerfil;
	private LocalDate dataNascimento;
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Long id, String nome, String email, String fotoPerfil, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.fotoPerfil = fotoPerfil;
		this.dataNascimento = dataNascimento;
	}
	
	public UsuarioDTO(Usuario entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
		this.fotoPerfil = entity.getFotoPerfil();
		this.dataNascimento = entity.getDataNascimento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Usuario toEntity() {
		return new Usuario(id, nome, email, fotoPerfil, dataNascimento, null);
	}
}
