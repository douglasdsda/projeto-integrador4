package com.integrador.enums;


public enum TipoInteresse {
	MARCA_PRESENCA(1),
	TENHO_INTERESSE(2),
	NAO_COMPARECEREI(3),
	INDIFERENTE(4);

	private int codigo;

	private TipoInteresse(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public static TipoInteresse valueOf(int codigo) {
		for (TipoInteresse value : TipoInteresse.values()) {
			if (value.getCodigo() == codigo) {
				return value;
			}

		}
		throw new IllegalMonitorStateException("Invalid TipoInteresse codigo");
	}
}
