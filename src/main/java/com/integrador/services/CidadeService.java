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

import com.integrador.dto.CidadeDTO;
import com.integrador.entities.Cidade;
import com.integrador.repository.CidadeRepository;
import com.integrador.services.exceptions.DatabaseException;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repository;

	
	public List<CidadeDTO> findAll() {
		List<Cidade> list = repository.findAll();
		return list.stream().map(e -> new CidadeDTO(e)).collect(Collectors.toList());
	}
	
	public CidadeDTO findById(Integer id) {
		Optional<Cidade> obj = repository.findById(id);
		Cidade entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new CidadeDTO(entity);
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
	public CidadeDTO update(Integer id, CidadeDTO obj) {
		try {
			Cidade entity = repository.getOne(id);
			updateData(entity, obj);
			entity = repository.save(entity);
			return new CidadeDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);			
		}
	}
	
	private void updateData(Cidade entity, CidadeDTO obj) {
		entity.setNome(obj.getNome());
	}
}
