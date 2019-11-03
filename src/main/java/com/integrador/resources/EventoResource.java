package com.integrador.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.dto.EventoDTO;
import com.integrador.services.EventoService;

@RestController
@RequestMapping(value = "/eventos")
public class EventoResource {

	@Autowired
	private EventoService service;

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<EventoDTO>> findAll() {
		List<EventoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<EventoDTO> findById(@PathVariable Integer id) {
		EventoDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
