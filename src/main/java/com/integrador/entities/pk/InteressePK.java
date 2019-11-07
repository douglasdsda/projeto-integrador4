package com.integrador.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.integrador.entities.Evento;
import com.integrador.entities.Usuario;

@Embeddable
public class InteressePK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "EVENTO_ID")
	private Evento evento;
	
	@ManyToOne
	@JoinColumn(name = "USUARIO_ID")
	private Usuario participante;

	public Evento getEventos() {
		return evento;
	}

	public void setEventos(Evento evento) {
		this.evento = evento;
	}

	public Usuario getParticipantes() {
		return participante;
	}

	public void setParticipantes(Usuario participante) {
		this.participante = participante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evento == null) ? 0 : evento.hashCode());
		result = prime * result + ((participante == null) ? 0 : participante.hashCode());
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
		InteressePK other = (InteressePK) obj;
		if (evento == null) {
			if (other.evento != null)
				return false;
		} else if (!evento.equals(other.evento))
			return false;
		if (participante == null) {
			if (other.participante != null)
				return false;
		} else if (!participante.equals(other.participante))
			return false;
		return true;
	}
}
