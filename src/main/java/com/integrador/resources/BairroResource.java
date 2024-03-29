package com.integrador.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.integrador.dto.BairroCadastroDTO;
import com.integrador.dto.BairroDTO;
import com.integrador.services.BairroService;

@RestController
@RequestMapping(value = "/bairro")
public class BairroResource {
	
	@Autowired
	private BairroService service;
	
	@GetMapping(value = "/findBairroCadastro")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<BairroCadastroDTO>> findBairroCadastro() {
		List<BairroCadastroDTO> list = service.findBairroCadastro();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<BairroDTO>> findAll() {
		List<BairroDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BairroDTO> findById(@PathVariable Integer id) {
		BairroDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<BairroDTO> update(@PathVariable Integer id, @Valid @RequestBody BairroDTO obj) {
		service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<BairroDTO> insert(@Valid @RequestBody BairroDTO obj) {
		BairroDTO newDTO = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(newDTO);
	}
}
