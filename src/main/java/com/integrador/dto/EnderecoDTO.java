package com.integrador.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.integrador.entities.Bairro;
import com.integrador.entities.Endereco;

public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "can't be empty")
	@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
	private String logradouro;
	
	private Integer numero;
		
	private String enderecoBairroNome;
	private Bairro enderecoBairro;

	@NotEmpty(message = "can't be empty")
	private String complemento;
	
	public EnderecoDTO() {
		
	}

	public EnderecoDTO(Integer id, String logradouro, Integer numero, String complemento) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
	}
	
	public EnderecoDTO(Endereco entity) {
		this.id = entity.getId();
		this.logradouro = entity.getLogradouro();
		this.numero = entity.getNumero();
		this.complemento = entity.getComplemento();
		this.enderecoBairroNome = entity.getBairro().getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setName(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}
	
	
	public String getEnderecoBairroNome() {
		return enderecoBairroNome;
	}

	public void setEnderecoBairro(String enderecoBairroNome) {
		this.enderecoBairroNome = enderecoBairroNome;
	}

	public Endereco toEntity() {
		return new Endereco(id, logradouro, numero, complemento, enderecoBairro);
	}
}
