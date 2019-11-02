package com.integrador.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.integrador.entities.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 201910311928L;

	private Integer id;

	@NotEmpty(message = "can't be empty")
	@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
	private String titulo;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.titulo = categoria.getTitulo();
	}

	public CategoriaDTO(Integer id, @Size(min = 1, message = "Tamanho minimo 1") String titulo) {
		this.id = id;
		this.titulo = titulo;
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

	public Categoria toEntity() {
		return new Categoria(id, titulo);
	}

}
