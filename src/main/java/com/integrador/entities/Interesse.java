package com.integrador.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.integrador.entities.pk.InteressePK;
import com.integrador.enums.TipoInteresse;

@Entity
@Table(name = "INTERESSE")
public class Interesse implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InteressePK id = new InteressePK();

	@Column(name = "TIPO_INTERESSE")
	private Integer tipoInteresse;

	@Column(name = "MOMENTO")
	private Instant momento;

	public Interesse() {
	}

	public Interesse(Usuario participantes,  Evento eventos,  TipoInteresse tipoInteresse, Instant momento) {
		super();
		setTipoInteresse(tipoInteresse);
		this.momento = momento;
		this.id.setUsuario(participantes);
		this.id.setEventos(eventos);
	}

	public TipoInteresse getTipoInteresse() {
		return TipoInteresse.valueOf(this.tipoInteresse);
	}

	public void setTipoInteresse(TipoInteresse tipoInteresse) {
		if(tipoInteresse != null) this.tipoInteresse = tipoInteresse.getCodigo();
	}
	
	public Instant getMomento() {
		return momento;
	}

	public void setMomento(Instant momento) {
		this.momento = momento;
	}

	public InteressePK getId() {
		return id;
	}

	public void setId(InteressePK id) {
		this.id = id;
	}
	
	public Evento getEventos() {
		return id.getEventos();
	}

	public void setEventos(Evento eventos) {
		this.id.setEventos(eventos);
	}

	public Usuario getUsuario() {
		return id.getUsuario();
	}

	public void setUsuario(Usuario usuario) {
		this.id.setUsuario(usuario);
	}
	

}
