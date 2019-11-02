package com.integrador.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
<<<<<<< HEAD
=======
import javax.validation.constraints.Size;
>>>>>>> e57d5eb6df4210f914c2faf0d21540015a601e38

import org.hibernate.validator.constraints.Length;

import com.integrador.entities.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "can't be empty")
	@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
	private String titulo;
	
	public CategoriaDTO() {
		
	}
	
	public CategoriaDTO(Long id, String titulo) {
		super();
		this.id = id;
		this.titulo = titulo;
	}
	
	public CategoriaDTO(Categoria entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
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
	
	public Categoria toEntity() {
		return new Categoria(id, titulo);
	}
}
