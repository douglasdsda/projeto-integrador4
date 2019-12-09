package com.integrador.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.integrador.entities.Bairro;
import com.integrador.entities.Cidade;
import com.integrador.entities.Estado;

public class BairroDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "can't be empty")
	@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
	private String nome;

	private Long cidadeId;
	private String cidadeNome;
	private Estado cidadeEstado;
		
	public BairroDTO() {
		
	}

	public BairroDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public BairroDTO(Bairro entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.cidadeId = entity.getCidade().getId();
		this.cidadeNome = entity.getCidade().getNome();
		this.cidadeEstado = entity.getCidade().getEstado();
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
	
	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getCidadeNome() {
		return cidadeNome;
	}

	public void setCidadeNome(String cidadeNome) {
		this.cidadeNome = cidadeNome;
	}

	public Estado getCidadeEstado() {
		return cidadeEstado;
	}

	public void setCidadeEstado(Estado cidadeEstado) {
		this.cidadeEstado = cidadeEstado;
	}

	public Bairro toEntity() {
		Cidade c = new Cidade(cidadeId, cidadeNome, cidadeEstado);
		return new Bairro(id, nome, c);
	}
}
