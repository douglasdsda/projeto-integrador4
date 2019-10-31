package com.integrador.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BAIRRO")
public class Bairro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BAIRRO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NOME")
	private String nome;	
	
    @ManyToOne
    @JoinColumn(name = "CIDADE_ID", nullable = false)
	private Cidade cidade;
	
	public Bairro() {
	}

	public Bairro(Integer id, String nome) {
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