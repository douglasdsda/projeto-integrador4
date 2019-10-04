package com.integrador.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CIDADE")
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CIDADE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NOME")
	private String nome;
	
	@OneToMany
	private Set<Bairro> bairros = new HashSet<>();
	

	public Cidade() {
	}

	public Cidade(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String nome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
