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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EVENTO")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EVENTO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "LOCAL_NOME")
	private String localNome;

	// @Column(name = "ENDERECO")
	// private String endereco;

	@Column(name = "DATA")
	private String data;

	// event type

	@Column(name = "DESCRICAO")
	private String descricao;

	// Categoria

	@ManyToMany
	@JoinTable(name = "EVENTO_CATEGORIA", joinColumns = @JoinColumn(name = "EVENTO_ID"), inverseJoinColumns = @JoinColumn(name = "CATEGORIA_ID"))
	private Set<Categoria> tipos = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "ENDERECO_ID")
	private Endereco endereco;
	
	// INTERESSE
	
	@OneToMany(mappedBy = "id.ventos")
	private Set<Interesse> interesses = new HashSet<>();

	public Evento() {
	}

	public Evento(Integer id, String titulo, String localNome, String data, String descricao) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.localNome = localNome;
		this.data = data;
		this.descricao = descricao;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Set<Categoria> getTipos() {
		return tipos;
	}
	
	public Set<Interesse> getInteresses() {
		return interesses;
	}


	/**
	 * 	METODO + getParticipants(): List<User> 
	 * */
	
	public Set<Usuario> getParticipantes(){
		Set<Usuario> set = new HashSet<>();
		for(Interesse x : interesses) {
			set.add(x.getParticipantes());
		}
		return set;
	} 
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
