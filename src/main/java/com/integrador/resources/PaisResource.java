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

import com.integrador.dto.PaisDTO;
import com.integrador.services.PaisService;

@RestController
@RequestMapping(value = "/pais")
public class PaisResource {
	
	@Autowired
	private PaisService service;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<PaisDTO>> findAll() {
		List<PaisDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PaisDTO> findById(@PathVariable Integer id) {
		PaisDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PaisDTO> update(@PathVariable Integer id, @Valid @RequestBody PaisDTO obj) {
		service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
