package com.integrador.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.integrador.entities.Bairro;
import com.integrador.entities.Endereco;
import com.integrador.entities.Evento;

public class EventoDTOeEnderecoDTO implements Serializable {
   
	

		private static final long serialVersionUID = 2019103119332L;

		private Long id;

		@NotEmpty(message = "can't be empty")
		@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
		private String titulo;

		@NotEmpty(message = "can't be empty")
		@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
		private String localNome;

		@NotNull(message = "ca't be null")
		private Instant data;

		@NotEmpty(message = "can't be empty")
		@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
		private String descricao;

		private Long enderecoId;

		private Long enderecoNumero;

		private String enderecoComplemento;

		private String enderecoLogradouro;
		
		private Long bairroId;
		
		Set<CategoriaDTO> categorias;
		
		public EventoDTOeEnderecoDTO() {}
		
		

		public EventoDTOeEnderecoDTO(Long id,
				@NotEmpty(message = "can't be empty") @Length(min = 3, max = 80, message = "string length must be between 3 and 80") String titulo,
				@NotEmpty(message = "can't be empty") @Length(min = 3, max = 80, message = "string length must be between 3 and 80") String localNome,
				@NotNull(message = "ca't be null") Instant data,
				@NotEmpty(message = "can't be empty") @Length(min = 3, max = 80, message = "string length must be between 3 and 80") String descricao,
				Long enderecoId, Long enderecoNumero, String enderecoComplemento, String enderecoLogradouro,
				Long bairroId, Set<CategoriaDTO> categorias) {
			super();
			this.id = id;
			this.titulo = titulo;
			this.localNome = localNome;
			this.data = data;
			this.descricao = descricao;
			this.enderecoId = enderecoId;
			this.enderecoNumero = enderecoNumero;
			this.enderecoComplemento = enderecoComplemento;
			this.enderecoLogradouro = enderecoLogradouro;
			this.bairroId = bairroId;
			this.categorias = categorias;
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

		public Long getEnderecoId() {
			return enderecoId;
		}

		public void setEnderecoId(Long enderecoId) {
			this.enderecoId = enderecoId;
		}

		public Long getEnderecoNumero() {
			return enderecoNumero;
		}

		public void setEnderecoNumero(Long enderecoNumero) {
			this.enderecoNumero = enderecoNumero;
		}

		public String getEnderecoComplemento() {
			return enderecoComplemento;
		}

		public void setEnderecoComplemento(String enderecoComplemento) {
			this.enderecoComplemento = enderecoComplemento;
		}

		public String getEnderecoLogradouro() {
			return enderecoLogradouro;
		}

		public void setEnderecoLogradouro(String enderecoLogradouro) {
			this.enderecoLogradouro = enderecoLogradouro;
		}

		public Long getBairroId() {
			return bairroId;
		}

		public void setBairroId(Long bairroId) {
			this.bairroId = bairroId;
		}
		
		public Endereco toEntityEndereco() {
			Bairro bairro = new Bairro(bairroId, null, null);
			return new Endereco(id, enderecoLogradouro, enderecoNumero, enderecoComplemento, bairro);
		}
		
		public Evento toEntityEvento() {
			Bairro bairro = new Bairro(bairroId, null, null);
			Endereco endereco = new Endereco(id, enderecoLogradouro, enderecoNumero, enderecoComplemento, bairro);
			return new Evento(id, titulo, localNome, data, descricao, endereco, categorias);
		}

}
