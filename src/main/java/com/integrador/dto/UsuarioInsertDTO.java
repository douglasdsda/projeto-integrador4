package com.integrador.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.integrador.entities.Usuario;
import com.integrador.services.validation.UsuarioInsertValid;

@UsuarioInsertValid
public class UsuarioInsertDTO implements Serializable {
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

	@NotEmpty(message = "can't be empty")
	private String password;
	
	public UsuarioInsertDTO() {
		
	}

	public UsuarioInsertDTO(Long id, String nome, String email, String fotoPerfil, LocalDate dataNascimento, String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.fotoPerfil = fotoPerfil;
		this.dataNascimento = dataNascimento;
		this.password = password;
	}
	
	public UsuarioInsertDTO(Usuario entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
		this.fotoPerfil = entity.getFotoPerfil();
		this.dataNascimento = entity.getDataNascimento();
		this.password = entity.getPassword();
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		String[] arrOfStr = dataNascimento.split("\\+");
		String dateInString = arrOfStr[0] + "Z";
        Instant instant = Instant.parse(dateInString);
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId())).toLocalDate();
		this.dataNascimento = localDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario toEntity() {
		return new Usuario(id, nome, email, fotoPerfil, dataNascimento, password, true);
	}
}
