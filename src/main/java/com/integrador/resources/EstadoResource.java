package com.integrador.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.dto.EstadoDTO;
import com.integrador.services.EstadoService;

@RestController
@RequestMapping(value = "/estado")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<EstadoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EstadoDTO> findById(@PathVariable Integer id) {
		EstadoDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EstadoDTO> update(@PathVariable Integer id, @Valid @RequestBody EstadoDTO obj) {
		service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
