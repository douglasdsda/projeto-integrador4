package com.integrador.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
/*
	@GetMapping(value = "/{id}")
	public ResponseEntity<EventoDTO> findById(@PathVariable Integer id) {
		EventoDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
*/
}
