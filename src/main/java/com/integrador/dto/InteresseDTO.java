package com.integrador.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.integrador.entities.Evento;
import com.integrador.entities.Interesse;
import com.integrador.entities.Usuario;
import com.integrador.enums.TipoInteresse;

public class InteresseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "can't be null")
	private TipoInteresse tipoInteresse;

	private Instant momento;

	private Long IdUsuario;

	@NotEmpty(message = "can't be empty")
	@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
	private String nomeUsuario;

	@Email(message = "email invalid")
	private String emailUsuario;

	private Integer IdEvento;

	@NotEmpty(message = "can't be empty")
	@Length(min = 3, max = 80, message = "string length must be between 3 and 80")
	private String tituloEvento;

	public InteresseDTO() {
	}

	public InteresseDTO(@NotNull(message = "can't be null") TipoInteresse tipoInteresse, Instant momento, Long idUsuario,
			String nomeUsuario, String emailUsuario, Integer idEvento, String tituloEvento) {
		super();
		this.tipoInteresse = tipoInteresse;
		this.momento = momento;
		IdUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
		IdEvento = idEvento;
		this.tituloEvento = tituloEvento;
	}

	public InteresseDTO(Interesse entity) {
		this.tipoInteresse = entity.getTipoInteresse();
		this.momento = entity.getMomento();
		IdUsuario = entity.getId().getParticipantes().getId();
		this.nomeUsuario = entity.getParticipantes().getNome();
		this.emailUsuario = entity.getParticipantes().getEmail();
		IdEvento = entity.getEventos().getId();
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
		return IdUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		IdUsuario = idUsuario;
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

	public Integer getIdEvento() {
		return IdEvento;
	}

	public void setIdEvento(Integer idEvento) {
		IdEvento = idEvento;
	}

	public String getTituloEvento() {
		return tituloEvento;
	}

	public void setTituloEvento(String tituloEvento) {
		this.tituloEvento = tituloEvento;
	}
	
	public Interesse toEntity() {
		Usuario user = new Usuario(IdUsuario, nomeUsuario, emailUsuario, null, null, null);
		Evento evento = new Evento(IdEvento, tituloEvento, null, null, null, null);

		return new Interesse(user, evento, tipoInteresse, momento);
	}

}
