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

import com.integrador.dto.CategoriaDTO;
import com.integrador.dto.UsuarioDTO;
import com.integrador.dto.UsuarioInsertDTO;
import com.integrador.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired 
	private UsuarioService service;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		UsuarioDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/info/{email}")
	public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable String email) {
		UsuarioDTO obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@Valid @RequestBody UsuarioInsertDTO obj) {
		UsuarioDTO newDTO = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(newDTO);
	}

	@PutMapping(value = "/{id}/addcategory")
	public ResponseEntity<Void> addCategory(@PathVariable Long id, @RequestBody CategoriaDTO categoryDto) {
		service.addCategory(id, categoryDto);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}/removecategory")
	public ResponseEntity<Void> removeCategory(@PathVariable Long id, @RequestBody CategoriaDTO categoryDto) {
		service.removeCategory(id, categoryDto);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}/setcategories")
	public ResponseEntity<Void> setCategories(@PathVariable Long id, @RequestBody List<CategoriaDTO> listCategoryDto) {
		service.setCategories(id, listCategoryDto);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO obj) {
		service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/seguidores/{usuarioId}")
	public ResponseEntity<List<UsuarioDTO>> findBySeguidores(@PathVariable Long usuarioId) {
		List<UsuarioDTO> dtos = service.findBySeguidores(usuarioId);
		return ResponseEntity.ok().body(dtos);
	}
}