package com.integrador.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;

public class InteresseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@NotNull(message = "can't be null")
	private Integer tipoInteresse;

	private Instant momento;
	
	
	
	
   public InteresseDTO() {}
   
   
   
   

}
