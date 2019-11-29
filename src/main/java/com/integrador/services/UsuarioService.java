package com.integrador.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.integrador.dto.CategoriaDTO;
import com.integrador.dto.UsuarioDTO;
import com.integrador.dto.UsuarioInsertDTO;
import com.integrador.entities.Categoria;
import com.integrador.entities.Usuario;
import com.integrador.repository.CategoriaRepository;
import com.integrador.repository.UsuarioRepository;
import com.integrador.services.exceptions.DatabaseException;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private CategoriaRepository categoryRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;	

	@Autowired
	private AuthService authService;
	
	public List<UsuarioDTO> findAll() {
		List<Usuario> list = repository.findAll();
		return list.stream().map(e -> new UsuarioDTO(e)).collect(Collectors.toList());
	}
	
	public UsuarioDTO findById(Long id) {
		authService.validateSelfOrAdmin(id);
		Optional<Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new UsuarioDTO(entity);
	}

	public UsuarioDTO findByEmail(String email) {
		Usuario obj = repository.findByEmail(email);
		if(obj == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UsuarioDTO(obj);
	}
	
	public UsuarioDTO insert(UsuarioInsertDTO obj) {
		Usuario entity = obj.toEntity();
		entity.setPassword(passwordEncoder.encode(obj.getPassword()));
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
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
	public UsuarioDTO update(Long id, UsuarioDTO obj) {
		authService.validateSelfOrAdmin(id);
		try {
			Usuario entity = repository.getOne(id);
			updateData(entity, obj);
			entity = repository.save(entity);
			return new UsuarioDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);			
		}
	}
	
	private void updateData(Usuario entity, UsuarioDTO obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setDataNascimento(obj.getDataNascimento());
		entity.setFotoPerfil(obj.getFotoPerfil());
	}

	@Override
	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = repository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user;		
	}
	
	@Transactional
	public void addCategory(Long id, CategoriaDTO dto) {
		authService.validateSelfOrAdmin(id);
		Usuario usuario = repository.getOne(id);
		Categoria categoria = categoryRepository.getOne(dto.getId());
		usuario.getCategorias().add(categoria);
		usuario.setFirstLogin(false);
		repository.save(usuario);
	}
	
	@Transactional
	public void removeCategory(Long id, CategoriaDTO dto) {
		authService.validateSelfOrAdmin(id);
		Usuario usuario = repository.getOne(id);
		Categoria categoria = categoryRepository.getOne(dto.getId());
		usuario.getCategorias().remove(categoria);
		repository.save(usuario);
	}

	@Transactional
	public void setCategories(Long id, List<CategoriaDTO> categoriesDto) {
		authService.validateSelfOrAdmin(id);
		Usuario usuario = repository.getOne(id);
		setUsuarioCategorias(usuario, categoriesDto);
		usuario.setFirstLogin(false);
		repository.save(usuario);
	}

	private void setUsuarioCategorias(Usuario entity, List<CategoriaDTO> categories) {
		entity.getCategorias().clear();
		for(CategoriaDTO dto : categories) {
			Categoria category = categoryRepository.getOne(dto.getId());
			entity.getCategorias().add(category);
		}
	}

	@Transactional
	public List<UsuarioDTO> findBySeguidores(Long usuarioId) {
		Set<Usuario> set = repository.getOne(usuarioId).getSeguidores();
		return set.stream().map(e -> new UsuarioDTO(e)).collect(Collectors.toList());
	}
}
