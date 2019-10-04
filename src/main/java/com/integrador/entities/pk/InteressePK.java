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
	private Evento ventos;
	
	@ManyToOne
	@JoinColumn(name = "USUARIO_ID")
	private Usuario participantes;

	public Evento getVentos() {
		return ventos;
	}

	public void setVentos(Evento ventos) {
		this.ventos = ventos;
	}

	public Usuario getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Usuario participantes) {
		this.participantes = participantes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((participantes == null) ? 0 : participantes.hashCode());
		result = prime * result + ((ventos == null) ? 0 : ventos.hashCode());
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
		if (participantes == null) {
			if (other.participantes != null)
				return false;
		} else if (!participantes.equals(other.participantes))
			return false;
		if (ventos == null) {
			if (other.ventos != null)
				return false;
		} else if (!ventos.equals(other.ventos))
			return false;
		return true;
	}
	
	

}
