package com.integrador.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.integrador.dto.InteresseDTO;
import com.integrador.services.InteresseService;

@RestController
@RequestMapping(value = "/interesse")
public class InteresseResource {

	@Autowired
	private InteresseService service;

	@GetMapping
	public ResponseEntity<List<InteresseDTO>> findAll() {
		List<InteresseDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<InteresseDTO> addInteresse(@Valid @RequestBody InteresseDTO interesseDTO) {
		interesseDTO = service.addInteresse(interesseDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(interesseDTO.getIdEvento()).toUri();
		return ResponseEntity.created(uri).body(interesseDTO);
	}
}
