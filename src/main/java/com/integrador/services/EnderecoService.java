package com.integrador.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.integrador.dto.BairroDTO;
import com.integrador.dto.CidadeDTO;
import com.integrador.dto.EnderecoDTO;
import com.integrador.dto.EstadoDTO;
import com.integrador.dto.ListaEnderecoDTO;
import com.integrador.dto.PaisDTO;
import com.integrador.entities.Endereco;
import com.integrador.repository.BairroRepository;
import com.integrador.repository.CidadeRepository;
import com.integrador.repository.EnderecoRepository;
import com.integrador.repository.EstadoRepository;
import com.integrador.repository.PaisRepository;
import com.integrador.services.exceptions.DatabaseException;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;
	
	@Autowired
	private PaisRepository repoPais;
	
	@Autowired
	private EstadoRepository repoEstado;;
	
	@Autowired
	private CidadeRepository repoCidade;
	
	@Autowired
	private BairroRepository repoBairro;
	
	public List<EnderecoDTO> findAll() {
		List<Endereco> list = repository.findAll();
		return list.stream().map(e -> new EnderecoDTO(e)).collect(Collectors.toList());
	}
	
	public EnderecoDTO findById(Integer id) {
		Optional<Endereco> obj = repository.findById(id);
		Endereco entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new EnderecoDTO(entity);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public EnderecoDTO update(Long id, EnderecoDTO obj) {
		try {
			Endereco entity = repository.getOne(id);
			updateData(entity, obj);
			entity = repository.save(entity);
			return new EnderecoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);			
		}
	}
	
	private void updateData(Endereco entity, EnderecoDTO obj) {
		entity.setLogradouro(obj.getLogradouro());
		entity.setNumero(obj.getNumero());
		entity.setComplemento(obj.getComplemento());
	}
	
	public EnderecoDTO insert(EnderecoDTO obj) {
		Endereco entity = obj.toEntity();
		entity = repository.save(entity);
		return new EnderecoDTO(entity);
	}

	@Transactional
	public ListaEnderecoDTO findListaEnderecos() {
		Set<PaisDTO> paises = repoPais.findAll().stream().map(e -> new PaisDTO(e)).collect(Collectors.toSet());
		Set<EstadoDTO> estados = repoEstado.findAll().stream().map(e -> new EstadoDTO(e)).collect(Collectors.toSet());
		Set<CidadeDTO> cidades = repoCidade.findAll().stream().map(e -> new CidadeDTO(e)).collect(Collectors.toSet());
		Set<BairroDTO> bairros = repoBairro.findAll().stream().map(e -> new BairroDTO(e)).collect(Collectors.toSet());
		 ListaEnderecoDTO lista = new ListaEnderecoDTO();
		 lista.getPaises().addAll(paises);
		 lista.getEstados().addAll(estados);
		 lista.getCidades().addAll(cidades);
		 lista.getBairros().addAll(bairros);
	
		return lista;
	}
	
}
