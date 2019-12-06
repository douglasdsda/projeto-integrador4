package com.integrador.dto;

import java.io.Serializable;

import com.integrador.entities.Bairro;

public class BairroCadastroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String nomeCidade;
	private String nomeEstado;
	private String nomePais;

	public BairroCadastroDTO() {
	}

	public BairroCadastroDTO(Long id, String nome, String nomeCidade) {
		this.id = id;
		this.nome = nome;
		this.nomeCidade = nomeCidade;
	}

	public BairroCadastroDTO(Bairro entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.nomeCidade = entity.getCidade().getNome();
		this.nomeEstado = entity.getCidade().getEstado().getNome();
		this.nomePais = entity.getCidade().getEstado().getPais().getNome();
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

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

}
