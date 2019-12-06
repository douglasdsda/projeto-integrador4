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

import com.integrador.dto.BairroCadastroDTO;
import com.integrador.dto.BairroDTO;
import com.integrador.entities.Bairro;
import com.integrador.repository.BairroRepository;
import com.integrador.services.exceptions.DatabaseException;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class BairroService {
	
	@Autowired
	private BairroRepository repository;

	
	public List<BairroDTO> findAll() {
		List<Bairro> list = repository.findAll();
		return list.stream().map(e -> new BairroDTO(e)).collect(Collectors.toList());
	}
	
	public BairroDTO findById(Integer id) {
		Optional<Bairro> obj = repository.findById(id);
		Bairro entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new BairroDTO(entity);
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
	public BairroDTO update(Integer id, BairroDTO obj) {
		try {
			Bairro entity = repository.getOne(id);
			updateData(entity, obj);
			entity = repository.save(entity);
			return new BairroDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);			
		}
	}
	
	private void updateData(Bairro entity, BairroDTO obj) {
		entity.setNome(obj.getNome());
	}
	
	public BairroDTO insert(BairroDTO obj) {
		Bairro entity = obj.toEntity();
		entity = repository.save(entity);
		return new BairroDTO(entity);
	}

	@Transactional
	public List<BairroCadastroDTO> findBairroCadastro() {
		return repository.findAll().stream().map(e -> new BairroCadastroDTO(e)).collect(Collectors.toList());
	}
	

}
