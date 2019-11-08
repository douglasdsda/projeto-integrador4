package com.integrador.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.integrador.dto.PaisDTO;
import com.integrador.entities.Pais;
import com.integrador.repository.PaisRepository;
import com.integrador.services.exceptions.DatabaseException;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class PaisService {
	
	@Autowired
	private PaisRepository repository;

	
	public List<PaisDTO> findAll() {
		List<Pais> list = repository.findAll();
		return list.stream().map(e -> new PaisDTO(e)).collect(Collectors.toList());
	}
	
	public PaisDTO findById(Integer id) {
		Optional<Pais> obj = repository.findById(id);
		Pais entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new PaisDTO(entity);
	}
	
	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public PaisDTO update(Integer id, PaisDTO obj) {
		try {
			Pais entity = repository.getOne(id);
			updateData(entity, obj);
			entity = repository.save(entity);
			return new PaisDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);			
		}
	}
	
	private void updateData(Pais entity, PaisDTO obj) {
		entity.setNome(obj.getNome());
	}
	
	public PaisDTO insert(PaisDTO obj) {
		Pais entity = obj.toEntity();
		entity = repository.save(entity);
		return new PaisDTO(entity);	
	}
}
