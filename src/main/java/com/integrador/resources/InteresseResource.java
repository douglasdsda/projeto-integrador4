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
import org.springframework.web.bind.annotation.RequestParam;
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

	@DeleteMapping(value = "/{idUsuario}/{idEvento}/")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Long idUsuario, @PathVariable Long idEvento) {
		service.removeInteresse(idUsuario, idEvento);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/usuario/{usuarioId}")
	public ResponseEntity<List<InteresseDTO>> findByCategoria(@PathVariable Long usuarioId){
		List<InteresseDTO> dtos = service.findByUsuario(usuarioId);
		return ResponseEntity.ok().body(dtos);
	}
	
	@GetMapping(value = "/seguidor/{usuarioId}")
	public ResponseEntity<List<InteresseDTO>> findSeguidorPeloUsuarioId(@PathVariable Long usuarioId){
		List<InteresseDTO> dtos = service.findSeguidorPeloUsuarioId(usuarioId);
		return ResponseEntity.ok().body(dtos);
	}
	
	@PutMapping(value = "/trocarTipoInteressePorUsuario")
	public ResponseEntity<Void> trocarTipoInteressePorUsuario(
			@RequestParam int escolha,
			@RequestParam Long eventoId,
			@RequestParam Long codUsuario
			){
		 service.trocarTipoInteressePorUsuario(escolha,eventoId,codUsuario );
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/pegarTipoInteresse")
	public  ResponseEntity<Integer> pegarTipoInteresse(
			@RequestParam Long eventoId,
			@RequestParam Long codUsuario
			){
		  int id = service.pegarTipoInteresse(eventoId,codUsuario );
		return ResponseEntity.ok().body(id);
	}
}
