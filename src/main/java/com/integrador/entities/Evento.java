package com.integrador.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

import org.springframework.transaction.annotation.Transactional;

import com.integrador.dto.CategoriaDTO;

@Entity
@Table(name = "EVENTO")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EVENTO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "LOCAL_NOME")
	private String localNome;

	@Column(name = "DATA")
	private Instant data;

	@Column(name = "DESCRICAO")
	private String descricao;

	// CATEGORIA
	@ManyToMany
	@JoinTable(name = "EVENTO_CATEGORIA", joinColumns = @JoinColumn(name = "EVENTO_ID"), inverseJoinColumns = @JoinColumn(name = "CATEGORIA_ID"))
	private Set<Categoria> tipos = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "ENDERECO_ID")
	private Endereco endereco;
	
	// INTERESSE	
	@OneToMany(mappedBy = "id.evento")
	private Set<Interesse> interesses = new HashSet<>();

	public Evento() {
	}

	public Evento(Long id, String titulo, String localNome, Instant data, String descricao, Endereco endereco, Set<CategoriaDTO> categorias) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.localNome = localNome;
		this.data = data;
		this.descricao = descricao;
		this.endereco = endereco;
		//if(categorias != null) this.tipos =  categorias.stream().map(e -> new CategoriaDTO().toEntity()).collect(Collectors.toSet());
	}

	public Set<Usuario> getParticipantes() {
		return interesses.stream().map(x -> x.getUsuario()).collect(Collectors.toSet());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Transactional
	public Set<Categoria> getTipos() {
		return tipos;
	}
	
	public Set<Interesse> getInteresses() {
		return interesses;
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
