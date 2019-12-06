package com.integrador.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.integrador.dto.EventoDTO;
import com.integrador.dto.EventoDTOeEnderecoDTO;
import com.integrador.services.EventoService;

@RestController
@RequestMapping(value = "/eventos")
public class EventoResource {

	@Autowired
	private EventoService service;

	@GetMapping
	public ResponseEntity<Page<EventoDTO>> findAllPaged(
			@RequestParam(value = "titulo", defaultValue = "") String titulo,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "endereco", defaultValue = "") String endereco,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "titulo") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<EventoDTO> list = service.findByNameCategory(titulo, categorias, pageRequest);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/listarFiltro")
	public ResponseEntity<List<EventoDTO>> listarFiltro(@RequestParam (value = "titulo") String titulo
			 ) {
		List<EventoDTO> list = service.listarFiltro(titulo);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/filtroCategoriaETitulo")
	public ResponseEntity<List<EventoDTO>> filtroCategoriaETitulo(
			@RequestParam (value = "categoria",defaultValue = "0" ) Long categoria
			,@RequestParam (value = "titulo", defaultValue = "") String titulo
			 ) {
		List<EventoDTO> list = service.filtroCategoriaETitulo(categoria, titulo);
		return ResponseEntity.ok().body(list);
	}
	
	
	@GetMapping(value = "/listar")
	public ResponseEntity<List<EventoDTO>> listar(
			 ) {
		List<EventoDTO> list = service.listar();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/categoria/{categoriaId}")
	public ResponseEntity<List<EventoDTO>> findByCategoria(@PathVariable Long categoriaId) {
		List<EventoDTO> dtos = service.findByCategoria(categoriaId);
		return ResponseEntity.ok().body(dtos);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<EventoDTO> findById(@PathVariable Long id) {
		EventoDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<EventoDTO> update(@PathVariable Long id, @Valid @RequestBody EventoDTO obj) {
		service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<EventoDTO> insert(  @RequestBody EventoDTO obj) {
		EventoDTO newDTO = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(newDTO);
	}

	@PostMapping("/addEventoEndereco")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<EventoDTO> insert(@Valid @RequestBody EventoDTOeEnderecoDTO dto) {
		EventoDTO newDTO = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(newDTO);
	}
	
	@PostMapping(value = "/categoriaEndereco")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<EventoDTO> categoriaEndereco(@Valid @RequestBody EventoDTO obj) {
		EventoDTO newDTO = service.categoriaEndereco(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(newDTO);
	}

}
