package com.integrador.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.integrador.entities.Estado;
import com.integrador.entities.Pais;

public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "can't be empty")
	@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
	private String nome;

	private Integer paisId;
	private String paisNome;
	
		
	public EstadoDTO() {
		
	}

	public EstadoDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public EstadoDTO(Estado entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.paisId = entity.getPais().getId();
		this.paisNome = entity.getPais().getNome();
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
	
	public Estado toEntity() {
		Pais p = new Pais(paisId, paisNome);
		return new Estado(id, nome, p);
	}

	public Integer getPaisId() {
		return paisId;
	}

	public void setPaisId(Integer paisId) {
		this.paisId = paisId;
	}

	public String getPaisNome() {
		return paisNome;
	}

	public void setPaisNome(String paisNome) {
		this.paisNome = paisNome;
	}
	
	
}
