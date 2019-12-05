package com.integrador.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;

import com.integrador.entities.Evento;
import com.integrador.entities.Interesse;
import com.integrador.entities.Usuario;
import com.integrador.enums.TipoInteresse;

public class InteresseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "can't be null")
	private TipoInteresse tipoInteresse;

	private Instant momento;

	@NotNull(message = "can't be null")
	private Long idUsuario;

	private String nomeUsuario;

	private String emailUsuario;

	@NotNull(message = "can't be null")
	private Long idEvento;

	private String tituloEvento;

	public InteresseDTO() {
	}

	public InteresseDTO(@NotNull(message = "can't be null") TipoInteresse tipoInteresse, Instant momento, Long idUsuario,
			String nomeUsuario, String emailUsuario, Long idEvento, String tituloEvento) {
		super();
		this.tipoInteresse = tipoInteresse;
		this.momento = momento;
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
		this.idEvento = idEvento;
		this.tituloEvento = tituloEvento;
	}

	public InteresseDTO(Interesse entity) {
		this.tipoInteresse = entity.getTipoInteresse();
		this.momento = entity.getMomento();
		idUsuario = entity.getId().getUsuario().getId();
		this.nomeUsuario = entity.getUsuario().getNome();
		this.emailUsuario = entity.getUsuario().getEmail();
		idEvento = entity.getEventos().getId();
		this.tituloEvento = entity.getEventos().getTitulo();
	}

	public TipoInteresse getTipoInteresse() {
		return tipoInteresse;
	}

	public void setTipoInteresse(TipoInteresse tipoInteresse) {
		this.tipoInteresse = tipoInteresse;
	}

	public Instant getMomento() {
		return momento;
	}

	public void setMomento(Instant momento) {
		this.momento = momento;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}
	
	public Long getIdEvento() {
		return idEvento;
	}

	public String getTituloEvento() {
		return tituloEvento;
	}

	public void setTituloEvento(String tituloEvento) {
		this.tituloEvento = tituloEvento;
	}
	
	public Interesse toEntity() {
		Usuario user = new Usuario(idUsuario, nomeUsuario, emailUsuario, null, null, null, false);
		Evento evento = new Evento(idEvento, tituloEvento, null, null, null, null,null);

		return new Interesse(user, evento, tipoInteresse, momento);
	}

}
