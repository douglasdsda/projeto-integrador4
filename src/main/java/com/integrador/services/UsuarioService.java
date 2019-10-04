package com.integrador.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.integrador.entities.Usuario;
import com.integrador.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario findById(Long id) throws Exception {
		Optional<Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new Exception());
		return entity;
	}
	
	public Usuario insert(Usuario obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) throws Exception {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
//			throw new ResourceNotFoundException(id);
			throw new Exception();
		} catch (DataIntegrityViolationException e) {
//			throw new DatabaseException(e.getMessage());
			throw new Exception();
		}
	}

	@Transactional
	public Usuario update(Long id, Usuario obj) throws Exception {
		try {
			Usuario entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new Exception();
//			throw new ResourceNotFoundException(id);
		}
		
	}
	
	private void updateData(Usuario entity, Usuario obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setFotoPerfil(obj.getFotoPerfil());
		entity.setDataNascimento(obj.getDataNascimento());
	}
}
