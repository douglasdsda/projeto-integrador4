package com.integrador.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ESTADO")
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ESTADO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NOME")
	private String nome;

    @ManyToOne
    @JoinColumn(name = "PAIS_ID", nullable = false)
	private Pais pais;
    
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
	private Set<Cidade> cidades = new HashSet<>();
	
	public Estado() {
	}

	public Estado(Integer id, String nome, Pais pais) {
		super();
		this.id = id;
		this.nome = nome;
		this.pais = pais;
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}	
	
}
