package com.integrador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.entities.PersonTest;
import com.integrador.repository.PersonTestRepository;

@Service
public class PersonTestService {
	
	@Autowired
	private PersonTestRepository repo;

	public List<PersonTest> findAllPersons() {
		return repo.findAll();
	}

}
