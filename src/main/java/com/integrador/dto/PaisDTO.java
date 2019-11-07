package com.integrador.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.integrador.entities.Pais;

public class PaisDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "can't be empty")
	@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
	private String nome;
		
	public PaisDTO() {
		
	}

	public PaisDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public PaisDTO(Pais entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
		
	public Pais toEntity() {
		return new Pais(id, nome);
	}
}
