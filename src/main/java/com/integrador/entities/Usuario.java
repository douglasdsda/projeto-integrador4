package com.integrador.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USUARIO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="NOME")
	private String nome;
	@Column(name="EMAIL")
	private String email;
	@Column(name="FOTO_PERFIL")
	private String fotoPerfil;
	@Column(name="DATA_NASCIMENTO")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinTable(name="usuario_seguidor",
		joinColumns={@JoinColumn(name="USUARIO_ID")},
		inverseJoinColumns={@JoinColumn(name="SEGUIDOR_ID")})
	private Set<Usuario> seguidores = new HashSet<Usuario>();
	

	@JsonIgnore
	@ManyToMany(mappedBy="seguidores", fetch=FetchType.EAGER)
	private Set<Usuario> influenciadores = new HashSet<Usuario>();
	
	//Categoria
	
	@ManyToMany
	@JoinTable(name = "USUARIO_CATEGORIA",
	joinColumns = @JoinColumn(name = "USUARIO_ID"),
		inverseJoinColumns = @JoinColumn(name = "CATEGORIA_ID")
			)
	private Set<Categoria> categorias = new HashSet<>();
	
	
	// INTERESSE
	@OneToMany(mappedBy = "id.participantes")
	private Set<Interesse> interesses = new HashSet<>();

	public Usuario() {
	}

	public Usuario(Long id, String nome, String email, String fotoPerfil, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.fotoPerfil = fotoPerfil;
		this.dataNascimento = dataNascimento;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFotoPerfil() {
		return fotoPerfil;
	}


	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public Set<Usuario> getSeguidores() {
		return seguidores;
	}


	public void setSeguidores(Set<Usuario> seguidores) {
		this.seguidores = seguidores;
	}


	public Set<Usuario> getInfluenciadores() {
		return influenciadores;
	}


	public void setInfluenciadores(Set<Usuario> influenciadores) {
		this.influenciadores = influenciadores;
	}
	
	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public int getAge() {
		return (int)(ChronoUnit.DAYS.between(this.dataNascimento, LocalDate.now()) / 365);
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
