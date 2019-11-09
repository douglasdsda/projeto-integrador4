package com.integrador.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.integrador.dto.InteresseDTO;
import com.integrador.entities.Evento;
import com.integrador.entities.Interesse;
import com.integrador.entities.Usuario;
import com.integrador.entities.pk.InteressePK;
import com.integrador.repository.EventoRepository;
import com.integrador.repository.InteresseRepository;
import com.integrador.repository.UsuarioRepository;
import com.integrador.services.exceptions.DatabaseException;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class InteresseService   {

	@Autowired
	private InteresseRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EventoRepository eventoRepository;	
	
	public List<InteresseDTO> findAll() {
		List<Interesse> list = repository.findAll();
		return list.stream().map(e -> new InteresseDTO(e)).collect(Collectors.toList());
	}
	
	public InteresseDTO findById(InteressePK id) {
		Interesse entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new InteresseDTO(entity);
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public InteresseDTO addInteresse(InteresseDTO interesseDTO) {
		Usuario usuario = usuarioRepository.getOne(interesseDTO.getIdUsuario());
		Evento evento = eventoRepository.getOne(interesseDTO.getIdEvento());
		
		Interesse interesse = new Interesse(usuario, evento, interesseDTO.getTipoInteresse(), Instant.now());
		repository.save(interesse);
		
//		evento.getInteresses().add(interesse);
//		eventoRepository.save(evento);
		
		return new InteresseDTO(interesse);
	}

	@Transactional
	public void removeInteresse(Long idUsuario, Long idEvento) {
		try {
			Usuario usuario = usuarioRepository.getOne(idUsuario);
			Evento evento = eventoRepository.getOne(idEvento);
			
			InteressePK interessePK = new InteressePK();
			interessePK.setEventos(evento);
			interessePK.setUsuario(usuario);
			
			repository.deleteById(interessePK);
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
