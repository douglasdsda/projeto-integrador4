package com.integrador.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.dto.InteresseDTO;
import com.integrador.entities.Interesse;
import com.integrador.entities.pk.InteressePK;
import com.integrador.repository.InteresseRepository;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class InteresseService   {

	@Autowired
	private InteresseRepository repository;
	
	
	public List<InteresseDTO> findAll() {
		List<Interesse> list = repository.findAll();
		return list.stream().map(e -> new InteresseDTO(e)).collect(Collectors.toList());
	}
	
	public InteresseDTO findById(InteressePK id) {
		Interesse entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new InteresseDTO(entity);
	}
	
	

 
}
