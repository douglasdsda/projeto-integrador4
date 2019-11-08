package com.integrador.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "BAIRRO")
public class Bairro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BAIRRO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME")
	private String nome;	
	
    @ManyToOne
    @JoinColumn(name = "CIDADE_ID", nullable = false)
	private Cidade cidade;
    
    @OneToMany(mappedBy = "bairro")
	private Set<Endereco> Enderecos = new HashSet<>();
	
	public Bairro() {
	}

	public Bairro(Long id, String nome, Cidade cidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Set<Endereco> getEnderecos() {
		return Enderecos;
	}
	
	
}
