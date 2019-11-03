package com.integrador.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.integrador.entities.Endereco;
import com.integrador.entities.Evento;

public class EventoDTO implements Serializable {

	private static final long serialVersionUID = 201910311932L;

	private Integer id;

	@NotEmpty(message = "can't be empty")
	@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
	private String titulo;

	@NotEmpty(message = "can't be empty")
	@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
	private String localNome;

	@NotNull(message = "ca't be null")
	private Instant data;

	@NotEmpty(message = "can't be empty")
	@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
	private String descricao;

	private Integer EnderecoId;

	private Integer EnderecoNumero;

	private String EnderecoComplemento;

	private String EnderecoLogradouro;

	public EventoDTO() {
	}

	public EventoDTO(Integer id, String titulo, String localNome, Instant data, String descricao) {
		this.id = id;
		this.titulo = titulo;
		this.localNome = localNome;
		this.data = data;
		this.descricao = descricao;
	}

	public EventoDTO(Evento entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.localNome = entity.getLocalNome();
		this.data = entity.getData();
		this.descricao = entity.getDescricao();
		this.EnderecoId = entity.getEndereco().getId();
		this.EnderecoNumero = entity.getEndereco().getNumero();
		this.EnderecoComplemento = entity.getEndereco().getComplemento();
		this.EnderecoLogradouro = entity.getEndereco().getLogradouro();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLocalNome() {
		return localNome;
	}

	public void setLocalNome(String localNome) {
		this.localNome = localNome;
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Evento toEntity() {
		Endereco endereco = new Endereco(EnderecoId, EnderecoLogradouro, EnderecoNumero, EnderecoComplemento);
		return new Evento(id, titulo, localNome, data, descricao, endereco);
	}

}
