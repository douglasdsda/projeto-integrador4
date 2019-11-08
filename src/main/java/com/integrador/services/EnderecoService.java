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

import com.integrador.dto.EnderecoDTO;
import com.integrador.entities.Endereco;
import com.integrador.entities.Evento;
import com.integrador.repository.EnderecoRepository;
import com.integrador.services.exceptions.DatabaseException;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;
	
	public List<EnderecoDTO> findAll() {
		List<Endereco> list = repository.findAll();
		return list.stream().map(e -> new EnderecoDTO(e)).collect(Collectors.toList());
	}
	
	public EnderecoDTO findById(Integer id) {
		Optional<Endereco> obj = repository.findById(id);
		Endereco entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new EnderecoDTO(entity);
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
	public EnderecoDTO update(Integer id, EnderecoDTO obj) {
		try {
			Endereco entity = repository.getOne(id);
			updateData(entity, obj);
			entity = repository.save(entity);
			return new EnderecoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);			
		}
	}
	
	private void updateData(Endereco entity, EnderecoDTO obj) {
		entity.setLogradouro(obj.getLogradouro());
		entity.setNumero(obj.getNumero());
		entity.setComplemento(obj.getComplemento());
	}
	
	public EnderecoDTO insert(EnderecoDTO obj) {
		Endereco entity = obj.toEntity();
		entity = repository.save(entity);
		return new EnderecoDTO(entity);
	}
	
}
