package com.integrador.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.integrador.entities.Cidade;
import com.integrador.entities.Estado;
import com.integrador.entities.Pais;

public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "can't be empty")
	@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
	private String nome;

	private Integer estadoId;
	private String estadoNome;
	private Pais estadoPais;
		
	public CidadeDTO() {
		
	}

	public CidadeDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		
	}
	
	public CidadeDTO(Cidade entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.estadoId = entity.getEstado().getId();
		this.estadoNome = entity.getEstado().getNome();
		this.estadoPais = entity.getEstado().getPais();
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
		
	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public String getEstadoNome() {
		return estadoNome;
	}

	public void setEstadoNome(String estadoNome) {
		this.estadoNome = estadoNome;
	}

	public Pais getEstadoPais() {
		return estadoPais;
	}

	public void setEstadoPais(Pais estadoPais) {
		this.estadoPais = estadoPais;
	}

	public Cidade toEntity() {
		Estado e = new Estado(estadoId, estadoNome, estadoPais);
		return new Cidade(id, nome, e);
	}
}
