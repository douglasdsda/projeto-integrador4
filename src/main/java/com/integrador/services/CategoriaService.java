package com.integrador.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.integrador.dto.CategoriaDTO;
import com.integrador.entities.Categoria;
import com.integrador.repository.CategoriaRepository;
import com.integrador.services.exceptions.DatabaseException;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public List<CategoriaDTO> findAll() {
		List<Categoria> list = repository.findAll();
		return list.stream().map(e -> new CategoriaDTO(e)).collect(Collectors.toList());
	}

	public CategoriaDTO findById(Long id) {
		Categoria entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new CategoriaDTO(entity);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public CategoriaDTO update(Long id, CategoriaDTO obj) {
		try {
			Categoria entity = repository.getOne(id);
			updateData(entity, obj);
			entity = repository.save(entity);
			return new CategoriaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Categoria entity, CategoriaDTO obj) {
		entity.setTitulo(obj.getTitulo());
	}

	public CategoriaDTO insert(CategoriaDTO obj) {
		Categoria entity = obj.toEntity();
		entity = repository.save(entity);
		return new CategoriaDTO(entity);
	}

}
