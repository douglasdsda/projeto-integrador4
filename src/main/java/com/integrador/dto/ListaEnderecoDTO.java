package com.integrador.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ListaEnderecoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	Set<PaisDTO> paises = new HashSet<>();
	Set<EstadoDTO> estados = new HashSet<>();
	Set<CidadeDTO> cidades = new HashSet<>();
	Set<BairroDTO> bairros = new HashSet<>();

	public ListaEnderecoDTO() {
	}

	public ListaEnderecoDTO(Set<PaisDTO> paises, Set<EstadoDTO> estados, Set<CidadeDTO> cidades,
			Set<BairroDTO> bairros) {
		this.paises = paises;
		this.estados = estados;
		this.cidades = cidades;
		this.bairros = bairros;
	}

	public Set<PaisDTO> getPaises() {
		return paises;
	}

	public void setPaises(Set<PaisDTO> paises) {
		this.paises = paises;
	}

	public Set<EstadoDTO> getEstados() {
		return estados;
	}

	public void setEstados(Set<EstadoDTO> estados) {
		this.estados = estados;
	}

	public Set<CidadeDTO> getCidades() {
		return cidades;
	}

	public void setCidades(Set<CidadeDTO> cidades) {
		this.cidades = cidades;
	}

	public Set<BairroDTO> getBairros() {
		return bairros;
	}

	public void setBairros(Set<BairroDTO> bairros) {
		this.bairros = bairros;
	}

}
