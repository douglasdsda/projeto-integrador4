package com.integrador.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.integrador.dto.EventoDTO;
import com.integrador.entities.Categoria;
import com.integrador.entities.Endereco;
import com.integrador.entities.Evento;
import com.integrador.repository.CategoriaRepository;
import com.integrador.repository.EnderecoRepository;
import com.integrador.repository.EventoRepository;
import com.integrador.services.exceptions.DatabaseException;
import com.integrador.services.exceptions.ParamFormatException;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class EventoService {

	@Autowired
	private EventoRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
 

	@Autowired
	private CategoriaRepository categoriaRepository;
 
	
	public List<EventoDTO> findAll() {
		List<Evento> list = repository.findAll();
		return list.stream().map(e -> new EventoDTO(e)).collect(Collectors.toList());
	}
	
	public Page<EventoDTO> findByNameCategory(String titulo, String categoriasStr, Pageable pageable) {
		Page<Evento> list;
		if(categoriasStr.equals("")) {
			list = repository.findByTituloContainingIgnoreCase(titulo, pageable);
		} else {
			List<Long> ids = parseIds(categoriasStr);
			List<Categoria> categorias = ids.stream().map(id -> categoriaRepository.getOne(id)).collect(Collectors.toList());
			list = repository.findByEventNameContainingIgnoreCaseAndCategoriesIn(titulo, categorias, pageable);
		}
		return list.map(e -> new EventoDTO(e));
	}
	
	private List<Long> parseIds(String categoriasStr) {
		String[] idsArray = categoriasStr.split(",");
		List<Long> listId = new ArrayList<>();
		for(String idStr : idsArray) {
			try {
			listId.add(Long.parseLong(idStr));
			} catch (NumberFormatException e) {
				throw new ParamFormatException("Invalid categories format");
			}
		}
		return listId;
	}

	public EventoDTO findById(Integer id) {
		Evento entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new EventoDTO(entity);
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
	public EventoDTO update(Integer id, EventoDTO obj) {
		try {
			Evento entity = repository.getOne(id);
			updateData(entity, obj);
			entity = repository.save(entity);
			return new EventoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Evento entity, EventoDTO obj) {
		entity.setData(obj.getData());
		entity.setDescricao(obj.getDescricao());
		entity.setLocalNome(obj.getLocalNome());
		entity.setTitulo(obj.getTitulo());
	}

	public EventoDTO insert(EventoDTO obj) {
		Evento entity = obj.toEntity();
		entity = repository.save(entity);
		return new EventoDTO(entity);
	}
 

	public List<EventoDTO> findByCategoria(Long categoriaId) {
		Categoria obj = categoriaRepository.getOne(categoriaId); 
		Set<Evento> set = obj.getEventos();
		return set.stream().map(e -> new EventoDTO(e)).collect(Collectors.toList());
	}
 
}
